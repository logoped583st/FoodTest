package com.logoped583.fruit.presentation.base

import androidx.paging.PageKeyedDataSource
import com.logoped583.fruit_tools.*
import com.logoped583.fruit_tools.utils.callWhenInternetIsAvailable
import com.logoped583.fruit_tools.utils.mapErrors
import com.logoped583.fruit_tools.utils.retryIfTimeOut
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

abstract class BaseDataSource<R : ListResponse<I>, I : ItemResponse> :
    PageKeyedDataSource<Int, I>(),
    IBaseDataSource<R, I> {

    protected val compositeDisposable = CompositeDisposable()

    override var isRefresh = false

    override val loadingStateImpl: LoadingStateObservable<List<I>, CustomExceptions> =
        LoadingStateObservable()

    abstract val networkListener: NetworkListener

    abstract val initialDbCall: Single<R>

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, I>
    ) {
        if (isRefresh) {
            loadingStateImpl.refresh()
            isRefresh = false
        } else {
            loadingStateImpl.startLoading()
        }

        compositeDisposable.add(
            networkListener.networkAvailable
                .subscribeOn(Schedulers.io())
                .firstOrError()
                .flatMap {
                    // test
                    //throw Exception("TEST")
                    if (it) {
                        apiCall
                    } else {
                        initialDbCall.flatMap {
                            if (it.items.isEmpty()) {
                                callWhenInternetIsAvailable(
                                    networkListener.networkAvailable,
                                    apiCall
                                )
                            } else {
                                Single.just(it)
                            }
                        }
                    }
                }
                .retryIfTimeOut()
                .observeOn(AndroidSchedulers.mainThread())
                .mapErrors {
                    loadingStateImpl.onError(it)
                }
                .subscribe({ data->
                    loadingStateImpl.dataReceived(data.items)
                    onInitialResult(data, callback)
                },{})
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, I>) {
        compositeDisposable.add(
            loadNextPage(params.key)
                .subscribe({
                    onResult(it, callback)
                }, {
                    Timber.e(it.message)
                })
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, I>) {

    }

    override fun invalidate() {
        compositeDisposable.clear()
        super.invalidate()
    }
}

interface IBaseDataSource<R : ListResponse<I>, I : ItemResponse> {

    fun onInitialResult(response: R, callback: PageKeyedDataSource.LoadInitialCallback<Int, I>)

    fun onResult(response: List<I>, callback: PageKeyedDataSource.LoadCallback<Int, I>)

    var isRefresh: Boolean

    fun invalidate()

    val loadingStateImpl: LoadingStateObservable<List<I>, CustomExceptions>

    val errorText: String

    val apiCall: Single<R>

    fun loadNextPage(offset: Int): Single<List<I>>
}
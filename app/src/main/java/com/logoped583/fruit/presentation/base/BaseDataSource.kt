package com.logoped583.fruit.presentation.base

import androidx.paging.PageKeyedDataSource
import com.logoped583.fruit_tools.*
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

/**
 *  DO NOT APPLY SCHEDULERS FOR RX SINGLES
 *  BY DEFAULT DATASOURCE USING IO SCHEDULER FOR FETCH DATA
 */

abstract class BaseDataSource<R : ListResponse<I>, I : ItemResponse> :
    PageKeyedDataSource<String, I>(),
    IBaseDataSource<R, I> {

    private val compositeDisposable = CompositeDisposable()

    override var isRefresh = false

    override val loadingStateImpl: LoadingStateObservable<List<I>, CustomExceptions> =
        LoadingStateObservable()

    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<String, I>
    ) {
        if (isRefresh) {
            loadingStateImpl.refresh()
            isRefresh = false
        } else {
            loadingStateImpl.startLoading()
        }

        compositeDisposable.add(
            apiCall
                .subscribe({
                    loadingStateImpl.dataReceived(it.items)
                    callback.onResult(it.items, null, null)
                }, {
                    Timber.e(it.message)
                })
        )
    }


    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, I>) {
        compositeDisposable.add(
            loadNextPage(params.key)
                .subscribe({
                    // callback.onResult(it, null)
                }, {
                    Timber.e(it.message)
                })
        )
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, I>) {
        Timber.e("LOAD BEFORE")
    }

    override fun invalidate() {
        compositeDisposable.clear()
        super.invalidate()
    }
}

interface IBaseDataSource<R : ListResponse<I>, I : ItemResponse> {

    var isRefresh: Boolean

    fun invalidate()

    val loadingStateImpl: LoadingStateObservable<List<I>, CustomExceptions>

    val errorText: String

    val apiCall: Single<R>

    fun loadNextPage(url: String): Single<R>
}
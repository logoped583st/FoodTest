package com.logoped583.fruit.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.logoped583.fruit_tools.*
import com.logoped583.fruit_tools.constants.listPagedConfig
import com.logoped583.fruit_tools.utils.mapErrors
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

abstract class BaseListLoadingViewModel<R : ListResponse<I>, I : ItemResponse>(
    private val factory: BaseDataSourceFactory<R, I>,
    networkListener: NetworkListener
) : BaseDisposableViewModel(networkListener), IBaseLoadingViewModel<List<I>> {

    override val state get() = factory.state

    val dataSource: LiveData<PagedList<I>> =
        LivePagedListBuilder(factory, listPagedConfig)
            .build()

    fun refresh() {
        factory.isRefresh = true
    }

    fun finishRefresh() {
        factory.isRefresh = false
    }

    fun clearPaging() {
        factory.invalidate()
    }

    override fun onCleared() {
        factory.invalidate()
        super.onCleared()
    }
}

abstract class BaseLoadingViewModel<Response>(networkListener: NetworkListener) :
    BaseDisposableViewModel(networkListener),
    IBaseLoadingViewModel<Response> {


    private val loadingState = LoadingStateLiveData<Response, CustomExceptions>()

    override val state: LiveData<LoadingStateSealed<Response, CustomExceptions>> =
        loadingState.state

    protected fun Single<Response>.loadingSubscriber(
        onSuccess: (data: Response) -> Unit = {},
        onError: (error: Throwable) -> Unit = {}
    ): Disposable {
        return doOnSuccess {
            loadingState.dataReceived(it)
        }.doOnSubscribe {
            addDisposable(it)
            loadingState.startLoading()
        }.mapErrors {
            loadingState.onError(it)
        }.doOnSuccess {
            loadingState.dataReceived(it)
        }.subscribe({
            onSuccess(it)
        }, {
            onError(it)
        })
    }

}

abstract class BaseDisposableViewModel(networkListener: NetworkListener) :
    ViewModel() {

    val compositeDisposable = CompositeDisposable()

    val networkState: MutableLiveData<Boolean> = MutableLiveData()

    init {
        addDisposable(
            networkListener.networkAvailable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    networkState.postValue(it)
                }
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}

interface IBaseLoadingViewModel<Response> {
    val state: LiveData<LoadingStateSealed<Response, CustomExceptions>>
}

fun BaseDisposableViewModel.addDisposable(disposable: Disposable) {
    compositeDisposable.add(disposable)
}

package com.logoped583.fruit.presentation.base

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.logoped583.fruit_tools.*
import com.logoped583.fruit_tools.constants.listPagedConfig
import com.logoped583.fruit_tools.utils.mapErrors
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseListLoadingViewModel<R : ListResponse<I>, I : ItemResponse>(
    private val factory: BaseDataSourceFactory<R, I>,
    context: Context,
    networkListener: NetworkListener
) : BaseDisposableViewModel(context, networkListener), IBaseLoadingViewModel<List<I>> {

    override val state = factory.state

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

abstract class BaseLoadingViewModel<Response>(context: Context, networkListener: NetworkListener) :
    BaseDisposableViewModel(context, networkListener),
    IBaseLoadingViewModel<Response> {


    private val loadingState = LoadingStateLiveData<Response, CustomExceptions>()

    override val state: LiveData<LoadingStateSealed<Response, CustomExceptions>> =
        loadingState.state

    protected fun Single<Response>.loadingSubscriber(
        onSuccess: (data: Response) -> Unit,
        onError: (error: Throwable) -> Unit
    ): Disposable {
        return doOnSuccess {
            loadingState.dataReceived(it)
        }.doOnSubscribe {
            addDisposable(it)
            loadingState.startLoading()
        }.mapErrors {
            loadingState.onError(it)
        }.subscribe({
            onSuccess(it)
        }, {
            onError(it)
        })
    }

}

abstract class BaseDisposableViewModel(val context: Context, networkListener: NetworkListener) :
    ViewModel() {

    val compositeDisposable = CompositeDisposable()

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

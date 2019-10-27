package com.logoped583.fruit.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.logoped583.fruit_tools.CustomExceptions
import com.logoped583.fruit_tools.ItemResponse
import com.logoped583.fruit_tools.ListResponse
import com.logoped583.fruit_tools.LoadingStateSealed
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Provider

abstract class BaseDataSourceFactory<R : ListResponse<I>, I : ItemResponse>
    (override val dataSourceProvider: Provider<out BaseDataSource<R, I>>)
    : DataSource.Factory<Int, I>(), IBaseDataSourceFactory<R, I> {

    private val stateImpl = MutableLiveData<LoadingStateSealed<List<I>, CustomExceptions>>()

    private lateinit var dataSource: BaseDataSource<R, I>

    var isRefresh = false

    private val compositeDisposable = CompositeDisposable()


    override fun create(): DataSource<Int,I> {
        dataSource = dataSourceProvider.get()
        dataSource.isRefresh = isRefresh

        compositeDisposable.add(dataSource.loadingStateImpl.state
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                stateImpl.postValue(it)
            })

        return dataSource
    }

    override fun invalidate() {
        compositeDisposable.clear()
        dataSource.invalidate()
    }

    override val state: LiveData<LoadingStateSealed<List<I>, CustomExceptions>> = stateImpl

}

interface IBaseDataSourceFactory<R : ListResponse<I>, I : ItemResponse> {

    val dataSourceProvider: Provider<out IBaseDataSource<R, I>>

    fun invalidate()

    val state: LiveData<LoadingStateSealed<List<I>, CustomExceptions>>
}


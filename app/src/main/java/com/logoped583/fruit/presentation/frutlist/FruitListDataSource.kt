package com.logoped583.fruit.presentation.frutlist

import com.example.fruit_models_mapper.FruitDbEntity
import com.example.fruit_models_mapper.FruitResponse
import com.logoped583.fruit.presentation.base.BaseDataSource
import com.logoped583.fruit.presentation.base.BaseDataSourceFactory
import com.logoped583.fruit.presentation.base.IBaseDataSource
import com.logoped583.fruit.presentation.base.IBaseDataSourceFactory
import com.logoped583.fruit_api.api.FruitApi
import com.logoped583.fruit_dao.dao.FruitListDao
import com.logoped583.fruit_tools.NetworkListener
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Provider


class FruitListDataSource @Inject constructor(
    private val fruitApi: FruitApi,
    private val fruitListDao: FruitListDao,
    override val networkListener: NetworkListener
) :
    BaseDataSource<FruitResponse, FruitDbEntity>(), IFruitListDataSource {
    
    override val initialDbCall: Single<FruitResponse>
        get() = fruitListDao.getFruitsPagination(offset).map { FruitResponse(it) }
    
    private var offset = 0
    override fun onResult(
        response: List<FruitDbEntity>,
        callback: LoadCallback<Int, FruitDbEntity>
    ) {
        Timber.e("NEXT RESPONSE $response")
        offset += 10
        callback.onResult(response, offset)
    }

    override fun onInitialResult(
        response: FruitResponse,
        callback: LoadInitialCallback<Int, FruitDbEntity>
    ) {
        compositeDisposable.add(
            Completable.fromCallable {
                fruitListDao.refreshFruits(response.items)
            }.subscribeOn(Schedulers.io())
                .andThen(
                    fruitListDao.getFruitsPagination(offset)
                )
                .subscribe({
                    offset += 10
                    callback.onResult(it, null, offset)
                }, {
                    Timber.e(it.message)
                })
        )
    }

    override val errorText: String
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override val apiCall: Single<FruitResponse>
        get() = fruitApi.getFruits()

    override fun loadNextPage(offset: Int): Single<List<FruitDbEntity>> {
        return fruitListDao.getFruitsPagination(offset)
    }
}

interface IFruitListDataSource : IBaseDataSource<FruitResponse, FruitDbEntity>

interface IFruitListDataSourceFactory : IBaseDataSourceFactory<FruitResponse, FruitDbEntity>

class FruitListDataSourceFactory @Inject constructor(
    dataSourceProvider: Provider<FruitListDataSource>
) : BaseDataSourceFactory<FruitResponse, FruitDbEntity>(dataSourceProvider),
    IFruitListDataSourceFactory
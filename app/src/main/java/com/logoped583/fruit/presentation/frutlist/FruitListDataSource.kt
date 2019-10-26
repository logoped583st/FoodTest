package com.logoped583.fruit.presentation.frutlist

import com.example.fruit_models_mapper.FruitDbEntity
import com.example.fruit_models_mapper.FruitResponse
import com.logoped583.fruit.presentation.base.BaseDataSource
import com.logoped583.fruit.presentation.base.BaseDataSourceFactory
import com.logoped583.fruit.presentation.base.IBaseDataSource
import com.logoped583.fruit.presentation.base.IBaseDataSourceFactory
import com.logoped583.fruit_api.api.FruitApi
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Provider

class FruitListDataSource @Inject constructor(private val fruitApi: FruitApi) :
    BaseDataSource<FruitResponse, FruitDbEntity>(), IFruitListDataSource {

    override val errorText: String
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override val apiCall: Single<FruitResponse>
        get() = fruitApi.getFruits()

    override fun loadNextPage(url: String): Single<FruitResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

interface IFruitListDataSource : IBaseDataSource<FruitResponse, FruitDbEntity>

interface IFruitListDataSourceFactory : IBaseDataSourceFactory<FruitResponse, FruitDbEntity>

class FruitListDataSourceFactory @Inject constructor(
    dataSourceProvider: Provider<FruitListDataSource>
) : BaseDataSourceFactory<FruitResponse, FruitDbEntity>(dataSourceProvider),
    IFruitListDataSourceFactory
package com.logoped583.fruit_api

import com.logoped583.fruit_api.api.FruitApi
import com.logoped583.fruit_api.models.Fruit
import io.reactivex.Single
import retrofit2.http.Path

internal class FruitSdkImpl(private val fruitApi: FruitApi) : FruitSDK {

    override fun getFruits(): Single<List<Fruit>> = fruitApi.getFruits()

    override fun getFruitDetails(id: String): Single<Fruit> = fruitApi.getFruitDetails(id)

}

interface FruitSDK : FruitApi
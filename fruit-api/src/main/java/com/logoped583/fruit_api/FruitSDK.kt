package com.logoped583.fruit_api

import com.example.fruit_models_mapper.FruitDetailsDbEntity
import com.example.fruit_models_mapper.FruitResponse
import com.logoped583.fruit_api.api.FruitApi
import io.reactivex.Single
import java.util.concurrent.TimeUnit

/**
 * Here we can use mocks or change api calls behavior
 */
internal class FruitSdkImpl(private val fruitApi: FruitApi) : FruitSDK {

    override fun getFruits(): Single<FruitResponse> =
        fruitApi.getFruits().delay(2, TimeUnit.SECONDS)

    override fun getFruitDetails(id: String): Single<FruitDetailsDbEntity> =
        fruitApi.getFruitDetails(id).delay(2, TimeUnit.SECONDS)

}

internal interface FruitSDK : FruitApi
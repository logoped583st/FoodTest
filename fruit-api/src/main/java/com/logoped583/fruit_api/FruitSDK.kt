package com.logoped583.fruit_api

import com.example.fruit_models_mapper.FruitDetailsResponse
import com.example.fruit_models_mapper.FruitResponse
import com.logoped583.fruit_api.api.FruitApi
import io.reactivex.Single

internal class FruitSdkImpl(private val fruitApi: FruitApi) : FruitSDK {

    override fun getFruits(): Single<FruitResponse> = fruitApi.getFruits()

    override fun getFruitDetails(id: String): Single<FruitDetailsResponse> =
        fruitApi.getFruitDetails(id)

}

internal interface FruitSDK : FruitApi
package com.logoped583.fruit_api.api

import com.example.fruit_models_mapper.FruitDetailsResponse
import com.example.fruit_models_mapper.FruitResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface FruitApi {

    @GET("cart/list")
    fun getFruits(): Single<FruitResponse>

    @GET("cart/{id}/detail")
    fun getFruitDetails(@Path("id") id: String): Single<FruitDetailsResponse>

}
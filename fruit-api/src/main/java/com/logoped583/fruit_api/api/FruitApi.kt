package com.logoped583.fruit_api.api

import com.logoped583.fruit_api.models.Fruit
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface FruitApi {

    @GET("cart/list")
    fun getFruits(): Single<List<Fruit>>

    @GET("cart/{id}/detail")
    fun getFruitDetails(@Path("id") id: String): Single<Fruit>

}
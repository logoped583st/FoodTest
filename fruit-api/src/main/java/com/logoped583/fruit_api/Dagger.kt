package com.logoped583.fruit_api

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.logoped583.fruit_api.api.FruitApi
import dagger.Component
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import javax.inject.Singleton

@Module
internal class ApiModule {

    private val fruitSDK: FruitSDK = FruitSdkImpl(provideFruitApi())

    @Provides
    @Singleton
    fun bindFruitApi(): FruitApi = fruitSDK
}

@Singleton
@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun fruitApi(): FruitApi
}

private fun provideFruitApi(): FruitApi {
    val interceptor = HttpLoggingInterceptor { msg ->
        Timber.i("okhttp $msg")
    }
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    return Retrofit.Builder()
        .client(OkHttpClient.Builder().addInterceptor(interceptor).build())
        .baseUrl("https://s3-eu-west-1.amazonaws.com/developer-application-test/")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(FruitApi::class.java) as FruitApi
}

fun apiComponent(): ApiComponent = DaggerApiComponent.builder()
    .apiModule(ApiModule())
    .build()


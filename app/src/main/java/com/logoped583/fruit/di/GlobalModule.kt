package com.logoped583.fruit.di

import com.logoped583.fruit_api.ApiComponent
import com.logoped583.fruit_api.api.FruitApi
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Module
class GlobalModule(private val component: ApiComponent) {

    private val cicerone: Cicerone<Router> = Cicerone.create()

    @Provides
    @Singleton
    fun provideFruitApi(): FruitApi = component.fruitApi()

    @Provides
    @Singleton
    fun provideCicerone(): Cicerone<Router> = cicerone
}

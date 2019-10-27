package com.logoped583.fruit.di

import android.content.Context
import com.logoped583.fruit_api.ApiComponent
import com.logoped583.fruit_api.api.FruitApi
import com.logoped583.fruit_dao.FruitDaoComponent
import com.logoped583.fruit_dao.dao.FruitDetailsDao
import com.logoped583.fruit_dao.dao.FruitListDao
import com.logoped583.fruit_tools.NetworkListener
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Module
class GlobalModule(
    private val component: ApiComponent,
    private val fruitDaoComponent: FruitDaoComponent,
    private val context: Context
) {

    private val cicerone: Cicerone<Router> = Cicerone.create()

    @Provides
    @Singleton
    fun provideFruitApi(): FruitApi = component.fruitApi()

    @Provides
    @Singleton
    fun provideCicerone(): Cicerone<Router> = cicerone

    @Provides
    @Singleton
    fun provideFruitListDao(): FruitListDao = fruitDaoComponent.fruitDao()

    @Provides
    @Singleton
    fun provideFruitDetailsDao(): FruitDetailsDao = fruitDaoComponent.fruitDetailsDao()

    @Provides
    @Singleton
    fun provideNetworkListener(): NetworkListener = NetworkListener()

    @Provides
    @Singleton
    fun provideContext(): Context {
        return context
    }
}

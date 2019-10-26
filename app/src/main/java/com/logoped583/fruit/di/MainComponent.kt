package com.logoped583.fruit.di

import com.logoped583.fruit.FruitApp
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        GlobalModule::class,
        AppModule::class,
        ViewModelModule::class
    ]
)
interface MainComponent : AndroidInjector<FruitApp> {

    @Component.Builder
    interface Builder {
        fun build(): MainComponent
        fun apiComponent(module: GlobalModule): Builder
    }

}


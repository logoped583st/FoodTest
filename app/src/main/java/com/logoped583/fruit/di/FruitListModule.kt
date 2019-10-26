package com.logoped583.fruit.di

import androidx.lifecycle.ViewModel
import com.logoped583.fruit.presentation.frutlist.FruitListDataSource
import com.logoped583.fruit.presentation.frutlist.FruitListDataSourceFactory
import com.logoped583.fruit.presentation.frutlist.FruitListViewModel
import com.logoped583.fruit.presentation.frutlist.IFruitListDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import dagger.multibindings.IntoMap
import javax.inject.Provider

@Module(includes = [AndroidInjectionModule::class, FruitListModule.RepositoriesDataSourceModule::class])
abstract class FruitListModule {

    @Binds
    @IntoMap
    @ViewModelKey(FruitListViewModel::class)
    abstract fun bindFruitListViewModel(fruitListViewModel: FruitListViewModel): ViewModel

    @Binds
    @FragmentScope
    abstract fun bindFruitListDataSource(fruitListDataSource: FruitListDataSource): IFruitListDataSource

    @Module
    class RepositoriesDataSourceModule {

        @Provides
        @FragmentScope
        fun provideFruitListDataSourceFactory(repositoriesDataSource: Provider<FruitListDataSource>): FruitListDataSourceFactory {
            return FruitListDataSourceFactory(repositoriesDataSource)
        }
    }
}
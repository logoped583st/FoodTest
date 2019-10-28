package com.logoped583.fruit.di

import androidx.lifecycle.ViewModel
import com.logoped583.fruit.presentation.fruifdetails.FruitDetailsRepository
import com.logoped583.fruit.presentation.fruifdetails.FruitDetailsRepositoryImpl
import com.logoped583.fruit.presentation.fruifdetails.FruitDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.multibindings.IntoMap

@Module(includes = [AndroidInjectionModule::class])
abstract class FruitDetailsModule {

    @Binds
    @IntoMap
    @ViewModelKey(FruitDetailsViewModel::class)
    abstract fun bindFruitDetailsViewModel(fruitListViewModel: FruitDetailsViewModel): ViewModel

    @Binds
    @FragmentScope
    abstract fun bindFruitDetailsRepository(fruitDetailsRepository: FruitDetailsRepositoryImpl): FruitDetailsRepository

}

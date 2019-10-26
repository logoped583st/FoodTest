package com.logoped583.fruit.di

import androidx.appcompat.app.AppCompatActivity
import com.logoped583.fruit.MainActivity
import com.logoped583.fruit.presentation.frutlist.FruitListFragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector
import javax.inject.Scope

@Scope
@MustBeDocumented
annotation class ActivityScope

@Scope
@MustBeDocumented
annotation class FragmentScope

@Module(includes = [AndroidInjectionModule::class])
abstract class AppModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun mainActivityInjector(): MainActivity

    @Module
    abstract class MainActivityModule {

        @Binds
        @ActivityScope
        abstract fun activity(mainActivity: MainActivity): AppCompatActivity

        @FragmentScope
        @ContributesAndroidInjector(modules = [FruitListModule::class])
        abstract fun fruitListFragmentInjector(): FruitListFragment

    }
}
package com.logoped583.fruit.di

import androidx.appcompat.app.AppCompatActivity
import com.logoped583.fruit.MainActivity
import com.logoped583.fruit.NetworkChangedBroadcastReceiver
import com.logoped583.fruit.presentation.fruifdetails.FruitDetailsFragment
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

    @ContributesAndroidInjector
    abstract fun contributesMyTestReceiver(): NetworkChangedBroadcastReceiver

    @Module
    abstract class MainActivityModule {

        @Binds
        @ActivityScope
        abstract fun activity(mainActivity: MainActivity): AppCompatActivity

        @FragmentScope
        @ContributesAndroidInjector(modules = [FruitListModule::class])
        abstract fun fruitListFragmentInjector(): FruitListFragment

        @FragmentScope
        @ContributesAndroidInjector(modules = [FruitDetailsModule::class])
        abstract fun fruitDetailsFragmentInjector(): FruitDetailsFragment

    }
}
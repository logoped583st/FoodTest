package com.logoped583.fruit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.logoped583.fruit.navigation.FruitListScreen
import com.logoped583.fruit.navigation.MainNavigator
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainActivity : AppCompatActivity(), Injectable, HasAndroidInjector {

    override fun androidInjector(): DispatchingAndroidInjector<Any> = dispatchingAndroidInjector

    @Inject
    lateinit var cicerone: Cicerone<Router>

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState==null){
            cicerone.router.replaceScreen(FruitListScreen())
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        cicerone.navigatorHolder.setNavigator(MainNavigator(this, supportFragmentManager, R.id.main_container))
    }

    override fun onPause() {
        cicerone.navigatorHolder.removeNavigator()
        super.onPause()
    }
}

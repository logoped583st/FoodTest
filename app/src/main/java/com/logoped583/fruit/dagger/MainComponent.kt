package com.logoped583.fruit.dagger

import com.logoped583.fruit.MainActivity
import dagger.Component
import dagger.android.AndroidInjector


@Component
interface AndroidInjectorComponent : AndroidInjector<MainActivity> {
}
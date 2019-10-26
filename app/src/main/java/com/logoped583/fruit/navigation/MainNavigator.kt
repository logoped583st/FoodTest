package com.logoped583.fruit.navigation

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class MainNavigator(
    activity: FragmentActivity?,
    fragmentManager: FragmentManager?,
    containerId: Int
) : SupportAppNavigator(activity, fragmentManager, containerId) {

}
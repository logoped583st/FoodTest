package com.logoped583.fruit.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.logoped583.fruit.R
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command

class MainNavigator(
    activity: FragmentActivity?,
    fragmentManager: FragmentManager?,
    containerId: Int
) : SupportAppNavigator(activity, fragmentManager, containerId) {

    override fun setupFragmentTransaction(
        command: Command,
        currentFragment: Fragment?,
        nextFragment: Fragment?,
        fragmentTransaction: FragmentTransaction?
    ) {
        fragmentTransaction?.setCustomAnimations(
            R.anim.in_from_bottom,
            R.anim.nav_default_exit_anim,
            R.anim.out_to_bottom,
            R.anim.nav_default_exit_anim
        )
        super.setupFragmentTransaction(command, currentFragment, nextFragment, fragmentTransaction)
    }
}
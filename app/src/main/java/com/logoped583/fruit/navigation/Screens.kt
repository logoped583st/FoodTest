package com.logoped583.fruit.navigation

import androidx.fragment.app.Fragment
import com.logoped583.fruit.presentation.frutlist.FruitListFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class FruitListScreen : SupportAppScreen() {
    override fun getFragment(): Fragment {
        return FruitListFragment()
    }
}

class FruitDetailsScreen : SupportAppScreen() {
}
package com.logoped583.fruit.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.fruit_models_mapper.FruitDbEntity
import com.logoped583.fruit.presentation.fruifdetails.FruitDetailsFragment
import com.logoped583.fruit.presentation.frutlist.FruitListFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class FruitListScreen : SupportAppScreen() {
    override fun getFragment(): Fragment {
        return FruitListFragment()
    }
}

class FruitDetailsScreen(private val fruitDbEntity: FruitDbEntity) : SupportAppScreen() {

    companion object {
        const val KEY_FRUIT = "KEY_FRUIT"
    }

    override fun getFragment(): Fragment {
        val bundle = Bundle().apply {
            putParcelable(KEY_FRUIT, fruitDbEntity)
        }

        val fragment = FruitDetailsFragment()
        fragment.arguments = bundle
        return fragment
    }
}
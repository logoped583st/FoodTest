package com.logoped583.fruit.presentation.frutlist

import com.example.fruit_models_mapper.FruitDbEntity
import com.example.fruit_models_mapper.FruitResponse
import com.logoped583.fruit.navigation.FruitDetailsScreen
import com.logoped583.fruit.presentation.base.BaseListLoadingViewModel
import com.logoped583.fruit_tools.NetworkListener
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class FruitListViewModel @Inject constructor(
    fruitListDataSourceFactory: FruitListDataSourceFactory,
    networkListener: NetworkListener,
    private val cicerone: Cicerone<Router>
) : BaseListLoadingViewModel<FruitResponse, FruitDbEntity>(
    fruitListDataSourceFactory,
    networkListener
) {
    fun navigateToDetails(fruitDbEntity: FruitDbEntity) {
        cicerone.router.navigateTo(FruitDetailsScreen(fruitDbEntity))
    }
}
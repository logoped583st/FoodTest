package com.logoped583.fruit.presentation.frutlist

import android.content.Context
import com.example.fruit_models_mapper.FruitDbEntity
import com.example.fruit_models_mapper.FruitResponse
import com.logoped583.fruit.presentation.base.BaseListLoadingViewModel
import com.logoped583.fruit_tools.NetworkListener
import javax.inject.Inject

class FruitListViewModel @Inject constructor(
    fruitListDataSourceFactory: FruitListDataSourceFactory,
    networkListener: NetworkListener,
    context: Context
) :
    BaseListLoadingViewModel<FruitResponse, FruitDbEntity>(
        fruitListDataSourceFactory,
        context,
        networkListener
    )
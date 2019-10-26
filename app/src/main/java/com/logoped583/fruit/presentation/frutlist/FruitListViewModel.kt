package com.logoped583.fruit.presentation.frutlist

import com.example.fruit_models_mapper.FruitDbEntity
import com.example.fruit_models_mapper.FruitResponse
import com.logoped583.fruit.presentation.base.BaseListLoadingViewModel
import javax.inject.Inject

class FruitListViewModel @Inject constructor(fruitListDataSourceFactory: FruitListDataSourceFactory) :
    BaseListLoadingViewModel<FruitResponse, FruitDbEntity>(fruitListDataSourceFactory)
package com.example.fruit_models_mapper

import com.logoped583.fruit_tools.ItemResponse

data class FruitModelUi(
    override val id: String,
    val name: String,
    val price: Int,
    val image: String
) : ItemResponse
package com.example.fruit_models_mapper

import com.google.gson.annotations.SerializedName
import com.logoped583.fruit_tools.ListResponse


data class FruitResponse(
    @SerializedName("products")
    override val items: List<FruitDbEntity>
) : ListResponse<FruitDbEntity>






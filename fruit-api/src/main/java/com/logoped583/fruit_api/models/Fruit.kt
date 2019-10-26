package com.logoped583.fruit_api.models

import com.google.gson.annotations.SerializedName

data class Fruit(
    @SerializedName("product_id")
    val id: String,
    val name: String,
    val price: Int,
    val image: String
)
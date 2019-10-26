package com.example.fruit_models_mapper

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.logoped583.fruit_tools.ItemResponse
import com.logoped583.fruit_tools.ListResponse


data class FruitResponse(
    @SerializedName("products")
    override val items: List<FruitDbEntity>
) : ListResponse<FruitDbEntity>

@Entity(tableName = "fruits")
data class FruitDbEntity(
    @PrimaryKey
    @SerializedName("product_id")
    override val id: String,
    val name: String,
    val price: Int,
    val image: String
) : ItemResponse

data class FruitDetailsResponse(
    @SerializedName("product_id")
    val id: String,
    val description: String
)


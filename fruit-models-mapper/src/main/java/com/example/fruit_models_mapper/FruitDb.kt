package com.example.fruit_models_mapper

import androidx.room.*
import com.google.gson.annotations.SerializedName
import com.logoped583.fruit_tools.ItemResponse

@Entity(
    tableName = "fruits",
    indices = [(Index(value = ["fruit_id"], unique = true))]
)
data class FruitDbEntity(
    @SerializedName("product_id")
    @field:ColumnInfo(name = "fruit_id")
    override val id: String,
    @field:ColumnInfo(name = "name")
    val name: String,
    @field:ColumnInfo(name = "price")
    val price: Int,
    @field:ColumnInfo(name = "image")
    val image: String
) : ItemResponse {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var idL: Long = 0
}

@Entity(
    tableName = "fruit_details",
    indices = [(Index(value = ["fruit_id"], unique = true))],
    foreignKeys = [ForeignKey(
        entity = FruitDbEntity::class,
        parentColumns = ["fruit_id"],
        onDelete = ForeignKey.NO_ACTION,
        childColumns = ["fruit_id"]
    )]
)
data class FruitDetailsDbEntity(
    @field:ColumnInfo(name = "fruit_id")
    val fruitId: String,
    @field:ColumnInfo(name = "description")
    val description: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0
}
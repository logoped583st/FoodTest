package com.example.fruit_models_mapper

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.logoped583.fruit_tools.ItemResponse
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
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
) : ItemResponse, Parcelable {
    @IgnoredOnParcel
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var idL: Long = 0
}

@Entity(
    tableName = "fruit_details",
    indices = [(Index(value = ["fruit_id"], unique = true))]
)
data class FruitDetailsDbEntity(
    @field:ColumnInfo(name = "fruit_id")
    @SerializedName("product_id")
    val fruitId: String,
    @field:ColumnInfo(name = "description")
    @SerializedName("description", alternate = ["decription"]) //F*CK OFF, I SPEND 30 MIN ON IT
    val description: String? // AND 5 ON It
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0
}
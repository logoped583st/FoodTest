package com.logoped583.fruit_dao.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.fruit_models_mapper.FruitDetailsDbEntity
import io.reactivex.Single

@Dao
interface FruitDetailsDao {

    @Query("SELECT * FROM FRUIT_DETAILS WHERE fruit_id =:id")
    fun getFruitDetail(id: String): Single<FruitDetailsDbEntity>

    @Query("UPDATE FRUIT_DETAILS SET description =:description WHERE FRUIT_DETAILS.fruit_id =:id")
    fun updateFruitDescription(description: String, id: String)

    @Query("DELETE FROM FRUIT_DETAILS where fruit_id NOT IN (SELECT FRUITS.fruit_id FROM FRUITS)")
    fun removeUnusedDescriptions()
}
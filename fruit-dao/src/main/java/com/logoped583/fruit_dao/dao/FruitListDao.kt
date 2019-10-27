package com.logoped583.fruit_dao.dao

import androidx.room.*
import com.example.fruit_models_mapper.FruitDbEntity
import io.reactivex.Single

@Dao
interface FruitListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFruits(fruits: List<FruitDbEntity>)

    @Transaction
    fun refreshFruits(fruits: List<FruitDbEntity>) {
        deleteAllFruits()
        insertFruits(fruits)
    }

    @Query("DELETE FROM FRUITS")
    fun deleteAllFruits()

    @Query("SELECT * FROM fruits LIMIT 10 OFFSET :offset")
    fun getFruitsPagination(offset: Int): Single<List<FruitDbEntity>>

    @Query("SELECT * FROM FRUITS")
    fun getAllFruits(): Single<List<FruitDbEntity>>


    @Query("SELECT COUNT(fruit_id) FROM FRUITS")
    fun getCount(): Single<Int>
}
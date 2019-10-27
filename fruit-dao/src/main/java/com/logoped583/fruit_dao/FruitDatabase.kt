package com.logoped583.fruit_dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.fruit_models_mapper.FruitDbEntity
import com.example.fruit_models_mapper.FruitDetailsDbEntity
import com.logoped583.fruit_dao.dao.FruitDetailsDao
import com.logoped583.fruit_dao.dao.FruitListDao

@Database(entities = [FruitDbEntity::class, FruitDetailsDbEntity::class], version = 1)
abstract class FruitDatabase : RoomDatabase() {

    abstract fun fruitDao(): FruitListDao

    abstract fun fruitDetailsDao(): FruitDetailsDao
}
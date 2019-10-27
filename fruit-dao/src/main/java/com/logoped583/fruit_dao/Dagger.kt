package com.logoped583.fruit_dao

import android.content.Context
import androidx.room.Room
import com.logoped583.fruit_dao.dao.FruitDetailsDao
import com.logoped583.fruit_dao.dao.FruitListDao
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Component(modules = [FruitDaoModule::class])
interface FruitDaoComponent {

    fun fruitDao(): FruitListDao

    fun fruitDetailsDao(): FruitDetailsDao
}

@Module
class FruitDaoModule(private val database: FruitDatabase) {

    @Provides
    @Singleton
    fun provideFruitDao(): FruitListDao = database.fruitDao()

    @Provides
    @Singleton
    fun bindFruitDetailsDao(): FruitDetailsDao = database.fruitDetailsDao()
}

fun daoComponent(context: Context): FruitDaoComponent {
    return DaggerFruitDaoComponent.builder()
        .fruitDaoModule(
            FruitDaoModule(
                Room.databaseBuilder(
                    context,
                    FruitDatabase::class.java,
                    "fruit-db"
                ).build()
            )
        ).build()

}
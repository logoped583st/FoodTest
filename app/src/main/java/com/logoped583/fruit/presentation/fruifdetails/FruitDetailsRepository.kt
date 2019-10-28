package com.logoped583.fruit.presentation.fruifdetails

import com.example.fruit_models_mapper.FruitDetailsDbEntity
import com.logoped583.fruit_api.api.FruitApi
import com.logoped583.fruit_dao.dao.FruitDetailsDao
import com.logoped583.fruit_tools.NetworkListener
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FruitDetailsRepositoryImpl @Inject constructor(
    private val fruitApi: FruitApi,
    private val fruitDetailsDao: FruitDetailsDao,
    private val networkListener: NetworkListener
) : FruitDetailsRepository {

    override fun getFruitDetails(id: String): Single<FruitDetailsDbEntity> {
        return networkListener.networkAvailable
            .first(true)
            .flatMap {
                if (it) {
                    fruitApi.getFruitDetails(id).doAfterSuccess {
                        Completable.fromCallable { fruitDetailsDao.insertFruitDescription(it) }
                            .subscribeOn(Schedulers.io())
                            .subscribe()
                    }
                } else {
                    fruitDetailsDao.getFruitDetail(id)
                }
            }
    }
}

interface FruitDetailsRepository {

    fun getFruitDetails(id: String): Single<FruitDetailsDbEntity>
}
package com.logoped583.fruit.presentation.fruifdetails

import com.example.fruit_models_mapper.FruitDetailsDbEntity
import com.logoped583.fruit.presentation.base.BaseLoadingViewModel
import com.logoped583.fruit.presentation.base.addDisposable
import com.logoped583.fruit_tools.NetworkListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class FruitDetailsViewModel @Inject constructor(
    networkListener: NetworkListener,
    private val fruitDetailsRepository: FruitDetailsRepository,
    private val cicerone: Cicerone<Router>
    ) : BaseLoadingViewModel<FruitDetailsDbEntity>(networkListener) {


    fun getFruitDetails(id: String) {
        addDisposable(
            fruitDetailsRepository.getFruitDetails(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .loadingSubscriber()
        )
    }

    fun exit() {
        cicerone.router.exit()
    }
}
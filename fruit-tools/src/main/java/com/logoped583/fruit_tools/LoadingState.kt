package com.logoped583.fruit_tools

import androidx.lifecycle.MutableLiveData
import io.reactivex.subjects.BehaviorSubject

sealed class LoadingStateSealed<DATA, ERROR> {
    class Start<DATA, ERROR> : LoadingStateSealed<DATA, ERROR>()
    class Loading<DATA, ERROR> : LoadingStateSealed<DATA, ERROR>()
    data class Data<DATA, ERROR>(val data: DATA) : LoadingStateSealed<DATA, ERROR>()
    data class Error<DATA, ERROR>(val error: ERROR) : LoadingStateSealed<DATA, ERROR>()
    class Refresh<DATA, ERROR> : LoadingStateSealed<DATA, ERROR>()
}

class LoadingStateLiveData<DATA, ERROR> {

    val state = MutableLiveData<LoadingStateSealed<DATA, ERROR>>()

    init {
        state.postValue(LoadingStateSealed.Start())
    }

}

class LoadingStateObservable<DATA, ERROR> {

    val state = BehaviorSubject.create<LoadingStateSealed<DATA, ERROR>>()

    init {
        state.onNext(LoadingStateSealed.Start())
    }
}

fun <T, C : CustomExceptions> LoadingStateLiveData<T, C>.startLoading() {
    state.postValue(LoadingStateSealed.Loading())
}

fun <T, C : CustomExceptions> LoadingStateLiveData<T, C>.dataReceived(data: T) {
    state.postValue(LoadingStateSealed.Data(data))
}

fun <T, C : CustomExceptions> LoadingStateLiveData<T, C>.onError(error: C) {
    state.postValue(LoadingStateSealed.Error(error))
}

fun <T, C : CustomExceptions> LoadingStateObservable<T, C>.startLoading() {
    state.onNext(LoadingStateSealed.Loading())
}

fun <T, C : CustomExceptions> LoadingStateObservable<T, C>.refresh() {
    state.onNext(LoadingStateSealed.Refresh())
}

fun <T, C : CustomExceptions> LoadingStateObservable<T, C>.dataReceived(data: T) {
    state.onNext(LoadingStateSealed.Data(data))
}

fun <T, C : CustomExceptions> LoadingStateObservable<T, C>.onError(error: C) {
    state.onNext(LoadingStateSealed.Error(error))
}
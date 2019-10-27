package com.logoped583.fruit_tools

import io.reactivex.subjects.BehaviorSubject

class NetworkListener {

    val networkAvailable: BehaviorSubject<Boolean> = BehaviorSubject.createDefault(true)

}
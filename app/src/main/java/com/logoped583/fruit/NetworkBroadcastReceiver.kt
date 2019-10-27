package com.logoped583.fruit

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.logoped583.fruit_tools.NetworkListener
import dagger.android.AndroidInjection
import javax.inject.Inject

class NetworkChangedBroadcastReceiver : BroadcastReceiver() {

    @Inject
    lateinit var networkListener: NetworkListener

    override fun onReceive(context: Context?, intent: Intent?) {
        AndroidInjection.inject(this, context)
        val connectivityManager =
            context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
        networkListener.networkAvailable.onNext(networkInfo?.isConnected ?: false)
    }

}
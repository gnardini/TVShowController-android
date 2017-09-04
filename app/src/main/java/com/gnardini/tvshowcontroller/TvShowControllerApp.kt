package com.gnardini.tvshowcontroller

import android.app.Application
import android.content.Context
import com.gnardini.tvshowcontroller.injector.CommonInjector

class TvShowControllerApp : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = this
    }

    companion object {

        lateinit var appContext: Context
            private set

        val commonInjector by lazy { CommonInjector() }
    }
}

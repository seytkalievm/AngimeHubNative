package com.seytkalievm.angimehubnative

import android.app.Application
import com.seytkalievm.angimehubnative.di.AppComponent
import com.seytkalievm.angimehubnative.di.DaggerAppComponent

open class MyApplication: Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}
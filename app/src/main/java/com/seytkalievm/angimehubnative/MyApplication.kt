package com.seytkalievm.angimehubnative

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Singleton

@HiltAndroidApp
@Singleton
open class MyApplication: Application()
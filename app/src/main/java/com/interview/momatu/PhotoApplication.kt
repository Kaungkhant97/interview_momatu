package com.interview.momatu

import android.app.Application
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PhotoApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
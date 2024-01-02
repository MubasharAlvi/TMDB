package com.example.projectdemo.presentation.main.view.baseapplication

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication: Application() {

    companion object{
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()

        context = this.applicationContext
    }
}
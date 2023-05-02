package com.esmt.cours.finalandroidprojet2023

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Singleton

@HiltAndroidApp
class MyApp: Application() {
    init {
        instance = this
    }

    companion object {
        private var instance: MyApp? = null

        val context: Context
            get() = instance!!.applicationContext
    }

}
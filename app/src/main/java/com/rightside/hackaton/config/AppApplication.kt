package com.rightside.hackaton.config


import androidx.multidex.MultiDexApplication
import com.google.firebase.FirebaseApp
import com.rightside.hackaton.config.di.modules
import org.koin.android.ext.android.startKoin



class AppApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        startKoin(this, modules)
    }
}
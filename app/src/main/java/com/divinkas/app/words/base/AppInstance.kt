package com.divinkas.app.words.base

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.divinkas.app.words.base.installer.KoinInstaller

class AppInstance : Application() {
    override fun onCreate() {
        super.onCreate()
        KoinInstaller.install(this)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}
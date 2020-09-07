package com.divinkas.app.words.modules

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.divinkas.app.words.modules.storage.LocalData

class LocalDataModule(
    private val application: Application
) {
    private fun providesSharedPreferences(): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(application)
    }

    fun provideSharedPreferencesRepo(): LocalData {
        return LocalData(providesSharedPreferences())
    }
}
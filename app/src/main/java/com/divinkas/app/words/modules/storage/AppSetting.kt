package com.divinkas.app.words.modules.storage

import android.content.SharedPreferences

class AppSetting(private val sharedPreferences: SharedPreferences) {
    companion object {
        private const val PREF_THEME_KEY = "pref_application_theme"
    }

    var isDarkTheme: Boolean
        get() = getBoolean(PREF_THEME_KEY)
        set(value) = setBoolean(PREF_THEME_KEY, value)

    private fun getBoolean(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    private fun setBoolean(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }
}
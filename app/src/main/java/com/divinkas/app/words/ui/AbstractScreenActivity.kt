package com.divinkas.app.words.ui

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.divinkas.app.words.R
import com.divinkas.app.words.helper.navigation.Navigator
import com.divinkas.app.words.modules.storage.AppSetting
import org.koin.android.ext.android.inject

abstract class AbstractScreenActivity : AppCompatActivity() {
    protected val navigator by inject<Navigator>()
    private val appSetting: AppSetting by inject()

    protected open val lightThemeRes = R.style.AppTheme_LightTheme
    protected open val darkThemeRes = R.style.AppTheme_DarkTheme

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        updateTheme()
    }

    private fun updateTheme() {
        if (appSetting.isDarkTheme) {
            setTheme(darkThemeRes)
        } else {
            setTheme(darkThemeRes)
        }
    }
}
package com.divinkas.app.words.ui.base

import androidx.appcompat.app.AppCompatActivity
import com.divinkas.app.words.helper.navigation.Navigator
import org.koin.android.ext.android.inject

abstract class AbstractScreenActivity : AppCompatActivity() {
    protected val navigator by inject<Navigator>()
}
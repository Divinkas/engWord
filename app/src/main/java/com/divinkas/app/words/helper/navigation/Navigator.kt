package com.divinkas.app.words.helper.navigation

import android.os.Bundle
import com.divinkas.app.words.helper.navigation.MetaScreen

interface Navigator {
    fun navigateToMetaScreen(metaScreen: MetaScreen, params: Bundle? = null)
    fun goToAppSettings()
    fun exit()
}
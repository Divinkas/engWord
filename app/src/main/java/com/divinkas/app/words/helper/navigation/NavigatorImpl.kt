package com.divinkas.app.words.helper.navigation

import android.content.Intent
import android.os.Bundle
import com.divinkas.app.words.AppInstance
import com.divinkas.app.words.ui.main.MainActivity

class NavigatorImpl(private val appInstance: AppInstance) : Navigator {
    override fun navigateToMetaScreen(metaScreen: MetaScreen, params: Bundle?) {
        when (metaScreen) {
            MetaScreen.MAIN -> {
                val intent = Intent(appInstance, MainActivity::class.java)
                params?.let { intent.putExtras(params) }
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                appInstance.startActivity(intent)
            }
            MetaScreen.TEST2 -> {
            }
            MetaScreen.TEST4 -> {
            }
            MetaScreen.SETTING -> {
            }
            MetaScreen.WORDS -> {
            }
        }
    }

    override fun goToAppSettings() {}

    override fun exit() {}
}
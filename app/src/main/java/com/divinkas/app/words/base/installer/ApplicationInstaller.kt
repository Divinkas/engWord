package com.divinkas.app.words.base.installer

import com.divinkas.app.words.AppInstance

interface ApplicationInstaller {
    fun install(appInstance: AppInstance)
}
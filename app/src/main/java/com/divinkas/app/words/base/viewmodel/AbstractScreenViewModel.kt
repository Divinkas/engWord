package com.divinkas.app.words.base.viewmodel

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.divinkas.app.words.helper.navigation.MetaScreen
import com.divinkas.app.words.helper.navigation.Navigator
import com.divinkas.app.words.modules.storage.LocalData
import org.koin.core.KoinComponent
import org.koin.core.inject

abstract class AbstractScreenViewModel : ViewModel(), KoinComponent {
    private val navigator: Navigator by inject()
    var navController: NavController? = null

    protected val localData: LocalData by inject()
    private val additionalSubscriptions: HashMap<LiveData<*>, ArrayList<Observer<*>>> = hashMapOf()

    fun navigateTo(metaScreen: MetaScreen) {
        val bundle = Bundle()
        navigator.navigateToMetaScreen(metaScreen, bundle)
    }

    fun <T> subscribeAndHandleLifeCycle(liveData: LiveData<T>, observer: Observer<T>) {
        additionalSubscriptions.let {
            if (it[liveData] == null) {
                it[liveData] = arrayListOf()
            }

            liveData.observeForever(observer)
            it[liveData]?.add(observer)
        }
    }

    override fun onCleared() {
        for ((liveData, observerList) in additionalSubscriptions) {
            for (observer in observerList) {
                liveData.removeObserver(observer as Observer<in Any>)
            }
        }
    }
}
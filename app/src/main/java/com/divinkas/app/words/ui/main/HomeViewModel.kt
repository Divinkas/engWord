package com.divinkas.app.words.ui.main

import com.divinkas.app.words.base.viewmodel.AbstractScreenViewModel

class HomeViewModel : AbstractScreenViewModel() {
    fun openWordFragment() {
        navController?.navigate(HomeFragmentDirections.actionHomeFragmentToWordFragment())
    }
}
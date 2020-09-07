package com.divinkas.app.words.base.livedata

import androidx.lifecycle.LiveData
import com.divinkas.app.words.base.viewmodel.LoadingViewState

open class LoadingViewStateLiveData<T> : LiveData<LoadingViewState<T>>() {
    protected open fun setState(state: LoadingViewState<T>) {
        value = state
    }

    protected open fun postState(state: LoadingViewState<T>) {
        postValue(state)
    }
}

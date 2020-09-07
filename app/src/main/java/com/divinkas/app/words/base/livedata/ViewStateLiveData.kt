package com.divinkas.app.words.base.livedata

import androidx.lifecycle.LiveData
import com.divinkas.app.words.base.viewmodel.ViewState

open class ViewStateLiveData<T> : LiveData<ViewState<T>>() {
    protected open fun setState(state: ViewState<T>) {
        value = state
    }

    protected open fun postState(state: ViewState<T>) {
        postValue(state)
    }
}

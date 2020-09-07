package com.divinkas.app.words.base.livedata

import com.divinkas.app.words.base.viewmodel.ViewState

class MutableViewStateLiveData<T> : ViewStateLiveData<T>() {
    public override fun setState(state: ViewState<T>) {
        super.setState(state)
    }

    public override fun postState(state: ViewState<T>) {
        super.postState(state)
    }
}

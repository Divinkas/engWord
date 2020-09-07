package com.divinkas.app.words.base.livedata

import com.divinkas.app.words.base.viewmodel.LoadingViewState

class MutableLoadingViewStateLiveData<T> : LoadingViewStateLiveData<T>() {
    public override fun setState(state: LoadingViewState<T>) {
        super.setState(state)
    }

    public override fun postState(state: LoadingViewState<T>) {
        super.postState(state)
    }
}

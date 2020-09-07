package com.divinkas.app.words.base.viewmodel

import com.divinkas.app.words.bean.ErrorLoad

sealed class LoadingViewState<T> {
    class Loading<T> : LoadingViewState<T>()
    class NextLoad<T> : LoadingViewState<T>()
    class Reload<T> : LoadingViewState<T>()
    class NoneObservable<T> : LoadingViewState<T>()
    class Fail<T> : LoadingViewState<T>()
    class FailWithError<T>(val error: ErrorLoad) : LoadingViewState<T>()
    class Success<T>(val response: T) : LoadingViewState<T>()
}
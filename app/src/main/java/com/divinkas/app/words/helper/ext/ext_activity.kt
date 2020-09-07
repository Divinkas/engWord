package com.divinkas.app.words.helper.ext

import android.app.Activity
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.divinkas.app.words.R
import com.divinkas.app.words.base.dialog.AbstractAlertDialog
import com.divinkas.app.words.base.livedata.LoadingViewStateLiveData
import com.divinkas.app.words.base.livedata.ViewStateLiveData
import com.divinkas.app.words.base.viewmodel.LoadingViewState
import com.divinkas.app.words.base.viewmodel.ViewState
import com.divinkas.app.words.utils.Configuration
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

fun <T : AbstractAlertDialog> AppCompatActivity.showDialog(dialog: T, block: T.() -> Unit = {}): T {
    supportFragmentManager.beginTransaction().apply {
        dialog.apply(block)
        supportFragmentManager.findFragmentByTag(Configuration.DIALOG_TAG)?.let { remove(it) }
        dialog.show(this, Configuration.DIALOG_TAG)
    }
    return dialog
}

fun <T> AppCompatActivity.observeViewState(
    liveData: ViewStateLiveData<T>,
    block: (data: T) -> Unit
) {
    liveData.observe(this, Observer {
        if (it is ViewState.Data) {
            it.data.apply(block)
        }
    })
}

fun <T> AppCompatActivity.observeViewState(
    liveData: LoadingViewStateLiveData<T>,
    block: (state: LoadingViewState<T>) -> Unit
) {
    liveData.observe(this, Observer {
        if (it !is LoadingViewState.NoneObservable) {
            it.apply(block)
        }
    })
}

fun <T> AppCompatActivity.observeLiveData(liveData: LiveData<T>, block: (data: T) -> Unit) {
    liveData.observe(this, Observer {
        block(it)
    })
}

fun Activity.showSnackBarWithAction(
    @StringRes msgRes: Int,
    @StringRes actionMsgRes: Int,
    duration: Int = Snackbar.LENGTH_LONG,
    snackBarDismissed: () -> Unit = {},
    actionClicked: () -> Unit
) {
    val snackBar = Snackbar.make(
        window.decorView.findViewById(android.R.id.content), msgRes, duration
    ).setAction(actionMsgRes) {
        actionClicked()
    }
    val textView: TextView =
        snackBar.view.findViewById(com.google.android.material.R.id.snackbar_text)
    textView.setTextColor(ContextCompat.getColor(baseContext, R.color.colorWhite))
    snackBar.addCallback(object : BaseTransientBottomBar.BaseCallback<Snackbar>() {
        override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
            super.onDismissed(transientBottomBar, event)
            snackBarDismissed()
        }
    })
    snackBar.show()
}

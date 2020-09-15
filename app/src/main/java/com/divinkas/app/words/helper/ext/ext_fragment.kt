package com.divinkas.app.words.helper.ext

import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.divinkas.app.words.R
import com.divinkas.app.words.base.dialog.AbstractAlertDialog
import com.divinkas.app.words.base.livedata.LoadingViewStateLiveData
import com.divinkas.app.words.base.livedata.ViewStateLiveData
import com.divinkas.app.words.base.viewmodel.LoadingViewState
import com.divinkas.app.words.base.viewmodel.ViewState
import com.divinkas.app.words.utils.Configuration
import com.divinkas.app.words.utils.DELAY_OPERATION_STANDARD
import com.google.android.material.snackbar.Snackbar

fun <T : AbstractAlertDialog> Fragment.showDialog(dialog: T, block: T.() -> Unit = {}): T {
    childFragmentManager.beginTransaction().apply {
        dialog.apply(block)
        childFragmentManager.findFragmentByTag(Configuration.DIALOG_TAG)?.let { remove(it) }
        dialog.show(this, Configuration.DIALOG_TAG)
    }
    return dialog
}

fun <T> Fragment.observeViewState(liveData: ViewStateLiveData<T>, block: (data: T) -> Unit) {
    liveData.observe(viewLifecycleOwner, Observer {
        if (it is ViewState.Data) {
            it.data.apply(block)
        }
    })
}

fun <T> Fragment.observeViewState(
    liveData: LoadingViewStateLiveData<T>,
    block: (state: LoadingViewState<T>) -> Unit
) {
    liveData.observe(viewLifecycleOwner, Observer {
        if (it !is LoadingViewState.NoneObservable) {
            it.apply(block)
        }
    })
}

fun <T> Fragment.observeLiveData(liveData: LiveData<T>, block: (data: T) -> Unit) {
    liveData.observe(viewLifecycleOwner, Observer {
        block(it)
    })
}

fun Fragment.addBackPressedCallback(action: () -> Unit) {
    requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object :
        OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            action()
        }
    })
}

fun Fragment.showSnackBar(@StringRes msgRes: Int, vararg args: Any) {
    showSnackBar(getString(msgRes, *args))
}

fun Fragment.showSnackBar(message: String) {
    showSnackBar(message, Snackbar.LENGTH_SHORT)
}

fun Fragment.showSnackBarAlways(message: String) {
    showSnackBar(message, Snackbar.LENGTH_INDEFINITE)
}

private fun Fragment.showSnackBar(message: String, type: Int) {
    val snackBar = Snackbar.make(view!!, message, type)
    val textView: TextView =
        snackBar.view.findViewById(com.google.android.material.R.id.snackbar_text)
    textView.setTextColor(ContextCompat.getColor(context!!, R.color.colorPrimary))
    snackBar.show()
}

fun Fragment.showSnackBarWithAction(
    @StringRes msgRes: Int,
    @StringRes actionMsgRes: Int,
    duration: Int = Snackbar.LENGTH_LONG,
    snackBarDismissed: () -> Unit = {},
    actionClicked: () -> Unit
) {
    activity?.showSnackBarWithAction(
        msgRes,
        actionMsgRes,
        duration,
        snackBarDismissed,
        actionClicked
    )
}

fun Fragment.toast(@StringRes msgRes: Int) {
    Toast.makeText(context, msgRes, Toast.LENGTH_SHORT).show()
}

fun Fragment.toast(@StringRes msgRes: Int, vararg formatArgs: Any) {
    Toast.makeText(context, getString(msgRes, *formatArgs), Toast.LENGTH_SHORT).show()
}

fun Fragment.toast(msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}

fun Fragment.runWithDelay(delayMillis: Long = DELAY_OPERATION_STANDARD, action: () -> Unit) {
    view!!.postDelayed(action, delayMillis)
}

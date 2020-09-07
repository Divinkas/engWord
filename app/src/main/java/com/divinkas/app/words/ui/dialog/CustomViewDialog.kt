package com.divinkas.app.words.ui.dialog

import android.view.View
import androidx.annotation.LayoutRes
import com.divinkas.app.words.base.dialog.AbstractAlertDialog

class CustomViewDialog(@LayoutRes private val layoutRes: Int) : AbstractAlertDialog(layoutRes) {
    var initView: (View) -> Unit = {}

    override fun initViews(view: View) {
        initView(view)
    }

    fun cancelDialog() {
        dismiss()
    }

    fun acceptDialog(block: () -> Unit = {}) {
        block()
        dismiss()
    }
}

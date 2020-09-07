package com.divinkas.app.words.base.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.DialogFragment

abstract class AbstractAlertDialog(@LayoutRes private val layoutRes: Int) : DialogFragment() {
    var rootView: View? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val customView = LayoutInflater.from(context).inflate(layoutRes, null)
        val dialog = AlertDialog.Builder(context)
            .setView(customView)
            .create().apply { setCanceledOnTouchOutside(false) }
        rootView = customView
        initViews(customView)
        return dialog
    }

    open fun initViews(view: View) {}
}

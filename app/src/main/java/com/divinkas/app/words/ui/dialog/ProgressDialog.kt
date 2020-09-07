package com.divinkas.app.words.ui.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.divinkas.app.words.R
import com.divinkas.app.words.base.dialog.AbstractAlertDialog

class ProgressDialog : AbstractAlertDialog(R.layout.dialog_progress) {
    override fun onCreateDialog(savedInstanceState: Bundle?) =
        super.onCreateDialog(savedInstanceState).apply {
            window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            isCancelable = false
        }
}
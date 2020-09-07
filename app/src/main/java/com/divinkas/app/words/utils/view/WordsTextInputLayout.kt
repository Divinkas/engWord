package com.divinkas.app.words.utils.view

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.Nullable
import com.google.android.material.textfield.TextInputLayout

/**
 *  Fixed default red background of TextInputLayout when editText field is wrong entered or empty
 *  resources https://stackoverflow.com/questions/39188609/how-to-fix-background-red-color-textinputlayout-when-isempty-in-android
 */

class WordsTextInputLayout : TextInputLayout {
    constructor(context: Context?) : super(context!!)
    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context!!, attrs, defStyleAttr)

    override fun drawableStateChanged() {
        super.drawableStateChanged()
        clearEditTextColorFilter()
    }

    override fun setError(@Nullable error: CharSequence?) {
        super.setError(error)
        clearEditTextColorFilter()
    }

    private fun clearEditTextColorFilter() {
        val editText = editText
        if (editText != null) {
            val background = editText.background
            background?.clearColorFilter()
        }
    }
}

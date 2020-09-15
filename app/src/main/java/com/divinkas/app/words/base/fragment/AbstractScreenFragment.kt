package com.divinkas.app.words.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.divinkas.app.words.R
import com.divinkas.app.words.base.viewmodel.AbstractScreenViewModel
import com.divinkas.app.words.helper.ext.runWithDelay
import com.divinkas.app.words.helper.ext.showDialog
import com.divinkas.app.words.ui.dialog.ProgressDialog
import com.divinkas.app.words.utils.MIN_PROGRESS_DURATION

abstract class AbstractScreenFragment<VM : AbstractScreenViewModel>(
    @LayoutRes protected val layout: Int
) : Fragment() {
    protected abstract val viewModel: VM
    private var progressDialog: ProgressDialog? = null
    private var progressShowedLastTime: Long = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(layout, container, false)
        setupDataBinding(view)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupNavArgs()
        setupUi()
        setupLiveDataObservers()
    }

    fun navigateTo(directions: NavDirections) {
        findNavController().navigate(directions)
    }

    protected fun showErrorLoadToast() {
        Toast.makeText(activity, getString(R.string.error_load), Toast.LENGTH_SHORT).show()
    }

    protected fun showProgressDialog() {
        progressDialog = showDialog(ProgressDialog())
        progressShowedLastTime = System.currentTimeMillis()
    }

    protected fun hideProgressDialog(onHide: () -> Unit) {
        val progressDialogDurationTime = System.currentTimeMillis() - progressShowedLastTime
        if (progressDialogDurationTime > MIN_PROGRESS_DURATION) {
            hideProgressDialogWithProperStateHandling()
            onHide()
        } else {
            runWithDelay(MIN_PROGRESS_DURATION - progressDialogDurationTime) {
                hideProgressDialogWithProperStateHandling()
                onHide()
            }
        }
    }

    private fun hideProgressDialogWithProperStateHandling() {
        progressDialog?.takeIf {
            it.dialog != null && it.dialog?.isShowing ?: false && !it.isRemoving
        }?.also {
            it.dismissAllowingStateLoss()
            progressDialog = null
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        progressDialog = null
    }

    open fun setupUi() {}
    open fun authRequired() {}
    open fun setupNavArgs() {}
    open fun setupDataBinding(view: View) {}
    open fun setupLiveDataObservers() {}
}

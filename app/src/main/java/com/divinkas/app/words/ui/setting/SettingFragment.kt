package com.divinkas.app.words.ui.setting

import com.divinkas.app.words.R
import com.divinkas.app.words.base.fragment.AbstractScreenFragment
import kotlinx.android.synthetic.main.fragment_setting.*
import org.koin.android.ext.android.inject

class SettingFragment : AbstractScreenFragment<SettingViewModel>(R.layout.fragment_setting) {
    override val viewModel: SettingViewModel by inject()

    override fun setupUi() {
        textViewAc.setOnClickListener {
            activity?.recreate()
        }
    }
}
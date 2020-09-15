package com.divinkas.app.words.ui.learn

import com.divinkas.app.words.R
import com.divinkas.app.words.base.fragment.AbstractScreenFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class LearnMainScreen : AbstractScreenFragment<LearnViewModel>(R.layout.learn_screen) {
    override val viewModel: LearnViewModel by viewModel()
}
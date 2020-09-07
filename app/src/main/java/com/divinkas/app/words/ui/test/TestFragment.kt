package com.divinkas.app.words.ui.test

import com.divinkas.app.words.R
import com.divinkas.app.words.base.fragment.AbstractScreenFragment
import org.koin.android.ext.android.inject

class TestFragment: AbstractScreenFragment<TestViewModel>(R.layout.fragment_question) {
    override val viewModel: TestViewModel by inject()
}
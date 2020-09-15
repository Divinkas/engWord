package com.divinkas.app.words.ui.statistic

import com.divinkas.app.words.R
import com.divinkas.app.words.base.fragment.AbstractScreenFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class StatisticMainScreen : AbstractScreenFragment<StatisticViewModel>(R.layout.statistic_screen) {
    override val viewModel: StatisticViewModel by viewModel()
}
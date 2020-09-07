package com.divinkas.app.words.ui.main

import android.view.View
import com.divinkas.app.words.R
import com.divinkas.app.words.base.fragment.AbstractScreenFragment
import com.divinkas.app.words.databinding.FragmentHomeBinding
import com.divinkas.app.words.helper.ext.bindView
import org.koin.android.ext.android.inject

class HomeFragment : AbstractScreenFragment<HomeViewModel>(R.layout.fragment_home) {
    override val viewModel: HomeViewModel by inject()
    private lateinit var binding: FragmentHomeBinding

    override fun setupDataBinding(view: View) {
        binding = bindView(view)
    }

    override fun setupUi() {
        binding.wordsContainer.setOnClickListener {
            viewModel.openWordFragment()
        }
        binding.questionContainer.setOnClickListener {
            // open test Fragment
        }
    }
}
package com.divinkas.app.words.ui.word

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.divinkas.app.words.R
import com.divinkas.app.words.base.fragment.AbstractScreenFragment
import com.divinkas.app.words.databinding.FragmentWordsBinding
import com.divinkas.app.words.helper.ext.bindView
import com.divinkas.app.words.helper.ext.observeLiveData
import com.divinkas.app.words.ui.word.adapter.BaseWordAdapter
import com.divinkas.app.words.utils.SpaceItemDecoration
import org.koin.androidx.viewmodel.ext.android.viewModel

class WordFragment : AbstractScreenFragment<WordViewModel>(R.layout.fragment_words) {
    override val viewModel: WordViewModel by viewModel()

    private lateinit var binding: FragmentWordsBinding
    private val adapter: BaseWordAdapter =
        BaseWordAdapter(ArrayList())

    override fun setupUi() {
        binding.wordsRecycler.layoutManager = LinearLayoutManager(context)
        binding.wordsRecycler.addItemDecoration(SpaceItemDecoration(0, context!!, adapter))
        binding.wordsRecycler.adapter = adapter

        setListeners()
        viewModel.loadData()
    }

    override fun setupDataBinding(view: View) {
        binding = bindView(view)
    }

    override fun setupLiveDataObservers() {
        observeLiveData(viewModel.wordCategoryLiveData) {
            adapter.clearData()
            adapter.addWords(it)
            binding.swipeWordLayout.isRefreshing = false
        }
    }

    private fun setListeners() {
        adapter.onClickListener = {
            // show word detail
        }

        binding.btnAddWord.setOnClickListener {
            viewModel.openWordManagerFragment()
        }

        binding.swipeWordLayout.setOnRefreshListener {
            adapter.clearData()
            viewModel.loadData()
        }
    }
}
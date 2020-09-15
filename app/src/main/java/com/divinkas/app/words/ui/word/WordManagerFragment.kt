package com.divinkas.app.words.ui.word

import android.view.View
import com.divinkas.app.words.R
import com.divinkas.app.words.base.fragment.AbstractScreenFragment
import com.divinkas.app.words.databinding.FragmentWordManagerBinding
import com.divinkas.app.words.helper.ext.bindView
import com.divinkas.app.words.helper.ext.observeLiveData
import com.divinkas.app.words.helper.ext.toast
import com.divinkas.app.words.ui.word.adapter.CategorySpinnerAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class WordManagerFragment : AbstractScreenFragment<WordViewModel>(R.layout.fragment_word_manager) {
    override val viewModel: WordViewModel by viewModel()

    private lateinit var binding: FragmentWordManagerBinding
    private lateinit var adapter: CategorySpinnerAdapter

    override fun setupDataBinding(view: View) {
        binding = bindView(view)
    }

    override fun setupUi() {
        adapter = CategorySpinnerAdapter(context!!, R.layout.item_category_spinner, ArrayList())
        binding.categorySpinner.adapter = adapter

        viewModel.loadCategories()
        setListeners()
    }

    override fun setupLiveDataObservers() {
        observeLiveData(viewModel.categoryLiveData) {
            adapter.addCategories(it.reversed())
        }
    }

    private fun setListeners() {
        binding.btnAddCategory.setOnClickListener {
            navigateTo(WordManagerFragmentDirections.actionWordManagerFragmentToCategoryManagerFragment())
        }

        binding.btnSaveWord.setOnClickListener {
            if (validateFields()) {
                viewModel.insertWord(
                    binding.etWord.text.toString(),
                    binding.etTranslate.text.toString(),
                    adapter.getItem(binding.categorySpinner.selectedItemPosition)!!.id!!
                )

                binding.etWord.setText("")
                binding.etTranslate.setText("")
                toast(R.string.word_is_added)
            }
        }
    }

    private fun validateFields(): Boolean {
        return !binding.etWord.text.isNullOrEmpty() && !binding.etTranslate.text.isNullOrEmpty() && adapter.count > 0
    }
}
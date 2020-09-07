package com.divinkas.app.words.ui.category

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.divinkas.app.words.R
import com.divinkas.app.words.base.fragment.AbstractScreenFragment
import com.divinkas.app.words.databinding.FragmentCategoryBinding
import com.divinkas.app.words.helper.ext.bindView
import com.divinkas.app.words.helper.ext.observeLiveData
import com.divinkas.app.words.helper.ext.showDialog
import com.divinkas.app.words.ui.adapter.recycler.CategoryAdapter
import com.divinkas.app.words.ui.dialog.CustomViewDialog
import com.divinkas.app.words.utils.SpaceItemDecoration
import kotlinx.android.synthetic.main.dialog_add_category.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoryManagerFragment :
    AbstractScreenFragment<CategoryViewModel>(R.layout.fragment_category) {
    override val viewModel: CategoryViewModel by viewModel()

    private lateinit var binding: FragmentCategoryBinding
    private lateinit var adapter: CategoryAdapter

    override fun setupDataBinding(view: View) {
        binding = bindView(view)
    }

    override fun setupUi() {
        adapter = CategoryAdapter(ArrayList())

        binding.categoryRecycler.layoutManager = LinearLayoutManager(context)
        binding.categoryRecycler.addItemDecoration(SpaceItemDecoration(4, context!!))
        binding.categoryRecycler.adapter = adapter

        binding.categorySwipeRefreshLayout.isRefreshing = true
        binding.categorySwipeRefreshLayout.setOnRefreshListener {
            adapter.clearCategories()
            binding.categorySwipeRefreshLayout.isRefreshing = true
            adapter.addCategories(viewModel.categoryLiveData.value!!)
            binding.categorySwipeRefreshLayout.isRefreshing = false
        }

        binding.btnAddCategory.setOnClickListener {
            showCustomViewDialog()
        }

        viewModel.loadCategories()
    }

    override fun setupLiveDataObservers() {
        observeLiveData(viewModel.categoryLiveData) {
            adapter.clearCategories()
            adapter.addCategories(it)
            binding.categorySwipeRefreshLayout.isRefreshing = false
        }
    }

    private fun showCustomViewDialog() {
        val dialog = CustomViewDialog(R.layout.dialog_add_category)
        dialog.initView = {
            it.dialogAddCategory.setOnClickListener {
                dialog.acceptDialog {
                    if (!dialog.rootView?.etCategory?.text.isNullOrEmpty()) {
                        viewModel.insertCategoryAsync(dialog.rootView?.etCategory?.text.toString())
                    }
                }
            }
            it.dialogBtnCancel.setOnClickListener { dialog.cancelDialog() }
        }
        showDialog(dialog)
    }
}
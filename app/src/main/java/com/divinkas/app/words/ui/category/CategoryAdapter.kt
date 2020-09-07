package com.divinkas.app.words.ui.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.divinkas.app.words.R
import com.divinkas.app.words.databinding.ItemCategoryBinding
import com.divinkas.app.words.bean.entities.Category

class CategoryAdapter(
    private val list: MutableList<Category>
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    fun addCategories(newCategories: List<Category>) {
        list.clear()
        list.addAll(newCategories)
        notifyDataSetChanged()
    }

    fun clearCategories() {
        list.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(
            DataBindingUtil.bind(view)!!
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.binding.category = list[position]
    }

    override fun getItemCount() = list.size

    class CategoryViewHolder(val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root)
}
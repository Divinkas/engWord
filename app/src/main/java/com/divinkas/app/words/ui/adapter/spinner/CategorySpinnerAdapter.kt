package com.divinkas.app.words.ui.adapter.spinner

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import com.divinkas.app.words.R
import com.divinkas.app.words.databinding.ItemCategorySpinnerBinding
import com.divinkas.app.words.domain.entities.Category

class CategorySpinnerAdapter(
    ctx: Context,
    @LayoutRes private val layout: Int,
    private val list: MutableList<Category>
) : ArrayAdapter<Category>(ctx, layout, list) {
    private val inflater: LayoutInflater = LayoutInflater.from(ctx)

    fun addCategories(newList: List<Category>) {
        list.addAll(newList)
        notifyDataSetChanged()
    }

    fun clearCategories() {
        list.clear()
        notifyDataSetChanged()
    }

    override fun getItemId(position: Int): Long = list[position].id!!.toLong()

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding: ItemCategorySpinnerBinding = DataBindingUtil.bind(
            inflater.inflate(R.layout.item_category_spinner, parent, false)
        )!!
        binding.category = list[position]
        return binding.root
    }

    override fun getCount() = list.size
}
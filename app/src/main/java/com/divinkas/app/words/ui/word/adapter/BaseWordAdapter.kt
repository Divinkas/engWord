package com.divinkas.app.words.ui.word.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.divinkas.app.words.R
import com.divinkas.app.words.databinding.ItemWordBinding
import com.divinkas.app.words.databinding.ItemWordHeaderBinding
import com.divinkas.app.words.bean.WordCategoryModel
import com.divinkas.app.words.utils.Constants
import com.divinkas.app.words.utils.StickyHeaderInterface

class BaseWordAdapter(
    private val list: MutableList<WordCategoryModel>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), StickyHeaderInterface {
    var onClickListener: (item: WordCategoryModel) -> Unit = {}

    fun addWords(newList: List<WordCategoryModel>) {
        list.addAll(newList)
        notifyItemRangeInserted(list.size - newList.size, newList.size)
    }

    fun clearData() {
        list.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == Constants.TYPE_WORD) {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_word, parent, false)
            BaseWordViewHolder(
                DataBindingUtil.bind(view)!!
            )
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_word_header, parent, false)
            CategoryLabelViewHolder(
                DataBindingUtil.bind(view)!!
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is BaseWordViewHolder) {
            holder.binding.word = list[position].word
            holder.binding.itemWordContainer.setOnClickListener {
                onClickListener(list[position])
            }
        }

        if (holder is CategoryLabelViewHolder) {
            holder.binding.tvWordHeader.text = list[position].category!!.categoryName
        }
    }

    override fun getItemCount() = list.size

    override fun getItemViewType(position: Int): Int {
        return if (list[position].isCategory) Constants.TYPE_CATEGORY else Constants.TYPE_WORD
    }

    class BaseWordViewHolder(
        val binding: ItemWordBinding
    ) : RecyclerView.ViewHolder(binding.root)

    class CategoryLabelViewHolder(
        val binding: ItemWordHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun isHeader(itemPosition: Int) = list[itemPosition].isCategory

    override fun getHeaderLayout(headerPosition: Int) = R.layout.item_word_header

    override fun getHeaderPositionForItem(itemPosition: Int): Int {
        var headerPosition = 0
        for (index in itemPosition downTo 0) {
            if (list[index].isCategory) {
                headerPosition = index
                break
            }
        }
        return headerPosition
    }

    override fun bindHeaderData(header: View, headerPosition: Int) {
        header.findViewById<TextView>(R.id.tvWordHeader).text =
            list[headerPosition].category!!.categoryName
    }
}
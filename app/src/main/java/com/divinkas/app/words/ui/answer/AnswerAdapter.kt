package com.divinkas.app.words.ui.answer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.divinkas.app.words.R
import com.divinkas.app.words.databinding.ItemAnswerBinding
import com.divinkas.app.words.bean.entities.Word

class AnswerAdapter(
    private var list: MutableList<Word>,
    private var rightWord: Word,
    private val block: (isRight: Boolean) -> Unit = {}
) : RecyclerView.Adapter<AnswerAdapter.AnswerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_answer, parent, false)
        return AnswerViewHolder(
            DataBindingUtil.bind(view)!!
        )
    }

    override fun onBindViewHolder(holder: AnswerViewHolder, position: Int) {
        holder.binding.isRightAnswer = list[position].id == rightWord.id
        holder.binding.word = list[position]
        holder.binding.tvWord.setOnClickListener {
            block(holder.binding.isRightAnswer!!)
        }
    }

    override fun getItemCount() = list.size

    class AnswerViewHolder(val binding: ItemAnswerBinding) : RecyclerView.ViewHolder(binding.root)
}
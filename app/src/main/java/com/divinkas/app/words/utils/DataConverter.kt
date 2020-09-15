package com.divinkas.app.words.utils

import com.divinkas.app.words.bean.WordCategoryModel
import com.divinkas.app.words.bean.entities.Category
import com.divinkas.app.words.bean.entities.Word

object DataConverter {
    fun createWordCategory(words: List<Word>, categories: List<Category>): List<WordCategoryModel> {
        val list: MutableList<WordCategoryModel> = ArrayList()

        categories.forEach { category ->
            val wordList = words.filter { it.kodCategory == category.id }
            if (!wordList.isNullOrEmpty()) {
                list.add(WordCategoryModel(category, null))
                wordList.forEach {
                    list.add(WordCategoryModel(null, it))
                }
            }
        }
        return list
    }
}
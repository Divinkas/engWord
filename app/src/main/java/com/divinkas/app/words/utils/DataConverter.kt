package com.divinkas.app.words.utils

import com.divinkas.app.words.bean.entities.Category
import com.divinkas.app.words.bean.entities.Word
import com.divinkas.app.words.bean.WordCategoryModel

object DataConverter {
    fun createWordCategory(words: List<Word>, categories: List<Category>): List<WordCategoryModel> {
        val list: MutableList<WordCategoryModel> = ArrayList()

        if (!words.isNullOrEmpty() && !categories.isNullOrEmpty()) {
            for (category in categories) {
                list.add(
                    WordCategoryModel(
                        category,
                        null,
                        true
                    )
                )
                for (word in words) {
                    if (category.id == word.kodCategory) {
                        list.add(
                            WordCategoryModel(
                                null,
                                word,
                                false
                            )
                        )
                    }
                }
            }
        }
        return list
    }
}
package com.divinkas.app.words.bean

import com.divinkas.app.words.bean.entities.Category
import com.divinkas.app.words.bean.entities.Word

data class WordCategoryModel(
    val category: Category? = null,
    val word: Word? = null,
    val isCategory: Boolean
) {
    constructor(category: Category?, word: Word?) : this(category, word, category != null)
}
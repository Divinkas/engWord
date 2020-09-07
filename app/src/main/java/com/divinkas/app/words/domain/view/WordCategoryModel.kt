package com.divinkas.app.words.domain.view

import com.divinkas.app.words.domain.entities.Category
import com.divinkas.app.words.domain.entities.Word

data class WordCategoryModel(
    val category: Category? = null,
    val word: Word? = null,
    val isCategory: Boolean
)
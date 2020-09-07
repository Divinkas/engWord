package com.divinkas.app.words.room.category

import androidx.lifecycle.LiveData
import com.divinkas.app.words.bean.entities.Category

interface CategoryRepository {
    fun insert(category: Category)
    fun loadAllCategories(): LiveData<List<Category>>
    fun update(category: Category)
    fun delete(id: Int)
}
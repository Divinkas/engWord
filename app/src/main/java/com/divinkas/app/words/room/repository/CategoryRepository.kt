package com.divinkas.app.words.room.repository

import androidx.lifecycle.LiveData
import com.divinkas.app.words.bean.entities.Category
import com.divinkas.app.words.room.db.EngWordsDB

class CategoryRepository(private val db: EngWordsDB) {

    fun insert(category: Category) {
        db.databaseWriteExecutor.execute {
            db.categoryDao().insert(category)
        }
    }

    fun loadCategories(): LiveData<List<Category>> = db.categoryDao().getCategories()
}
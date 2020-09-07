package com.divinkas.app.words.room.category

import androidx.lifecycle.LiveData
import com.divinkas.app.words.bean.entities.Category
import com.divinkas.app.words.room.EngWordsDB

class CategoryRepositoryImpl(private val db: EngWordsDB):
    CategoryRepository {

    override fun insert(category: Category) {
        db.databaseWriteExecutor.execute {
            db.categoryDao().insert(category)
        }
    }

    override fun loadAllCategories(): LiveData<List<Category>> {
        return db.categoryDao().getCategories()
    }

    override fun update(category: Category) {
        db.categoryDao().update(category)
    }

    override fun delete(id: Int) {
        // db.categoryDao().delete(id)
    }

    fun loadCategories(): LiveData<List<Category>> = db.categoryDao().getCategories()
}
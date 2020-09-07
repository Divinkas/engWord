package com.divinkas.app.words.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.divinkas.app.words.domain.entities.Category
import com.divinkas.app.words.utils.ConstRoom

@Dao
interface ICategoryDao {
    @Query("SELECT * from " + ConstRoom.TABLE_CATEGORY + " ORDER BY " + ConstRoom.TB_CATEGORY_ID + " ASC")
    fun getCategories(): LiveData<List<Category>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(category: Category)
}
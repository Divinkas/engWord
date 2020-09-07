package com.divinkas.app.words.room.category

import androidx.lifecycle.LiveData
import androidx.room.*
import com.divinkas.app.words.bean.entities.Category
import com.divinkas.app.words.utils.ConstRoom

@Dao
interface ICategoryDao {
    @Query("SELECT * from " + ConstRoom.TABLE_CATEGORY + " ORDER BY " + ConstRoom.TB_CATEGORY_ID + " ASC")
    fun getCategories(): LiveData<List<Category>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(category: Category)

    @Update
    fun update(category: Category)
}
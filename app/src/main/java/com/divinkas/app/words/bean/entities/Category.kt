package com.divinkas.app.words.bean.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.divinkas.app.words.utils.ConstRoom

@Entity(tableName = ConstRoom.TABLE_CATEGORY)
data class Category(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ConstRoom.TB_CATEGORY_ID)
    val id: Int? = null,

    @ColumnInfo(name = ConstRoom.TB_CATEGORY_NAME)
    var categoryName: String
)
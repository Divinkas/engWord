package com.divinkas.app.words.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.divinkas.app.words.utils.ConstRoom

@Entity(tableName = ConstRoom.TABLE_WORD)
data class Word(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ConstRoom.TB_WORD_ID)
    val id: Int? = null,

    @ColumnInfo(name = ConstRoom.TB_WORD_WORD)
    var word: String,

    @ColumnInfo(name = ConstRoom.TB_WORD_TRANSLATE)
    var translate: String,

    @ColumnInfo(name = ConstRoom.TB_WORD_KOD_CATEGORY)
    var kodCategory: Int,

    @ColumnInfo(name = ConstRoom.TB_WORD_DATE_ADDED)
    var dateAdding: String,

    @ColumnInfo(name = ConstRoom.TB_WORD_DATE_LAST_VISIBLE)
    var lastVisibleDate: String,

    @ColumnInfo(name = ConstRoom.TB_WORD_IS_LEARNED)
    var isLearned: Boolean,

    @ColumnInfo(name = ConstRoom.TB_WORD_RIGHT_ANSWERS)
    var rightAnswersCount: Int,

    @ColumnInfo(name = ConstRoom.TB_WORD_WRONG_ANSWERS)
    var wrongAnswersCount: Int
)
package com.divinkas.app.words.bean.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.divinkas.app.words.utils.ConstRoom

@Entity(tableName = ConstRoom.TABLE_QUESTION_WORD)
data class QuestionWord(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ConstRoom.TB_QW_ID)
    var id: Int? = null,

    @ColumnInfo(name = ConstRoom.TB_QW_WORD)
    var word: String,

    @ColumnInfo(name = ConstRoom.TB_QW_QUESTION)
    var question: String,

    @ColumnInfo(name = ConstRoom.TB_QW_IS_LEARNED)
    var isLearned: Boolean,

    @ColumnInfo(name = ConstRoom.TB_QW_RIGHT_ANSWERS)
    var rightAnswersCount: Int,

    @ColumnInfo(name = ConstRoom.TB_QW_WRONG_ANSWERS)
    var wrongAnswersCount: Int
)
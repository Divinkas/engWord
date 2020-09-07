package com.divinkas.app.words.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.divinkas.app.words.bean.entities.QuestionWord
import com.divinkas.app.words.bean.entities.Word
import com.divinkas.app.words.utils.ConstRoom

@Dao
interface IWordsDao {
    @Query("SELECT * from " + ConstRoom.TABLE_WORD + " ORDER BY " + ConstRoom.TB_WORD_WORD + " ASC")
    fun getBaseWords(): LiveData<List<Word>>

    @Query("SELECT * from " + ConstRoom.TABLE_QUESTION_WORD + " ORDER BY " + ConstRoom.TB_QW_WORD + " ASC")
    fun getQuestionsWords(): LiveData<List<QuestionWord>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(word: Word)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(word: QuestionWord)

    @Update
    fun update(word: Word)

    @Update
    fun update(word: QuestionWord)

    @Delete
    fun delete(word: Word)

    @Delete
    fun delete(word: QuestionWord)
}
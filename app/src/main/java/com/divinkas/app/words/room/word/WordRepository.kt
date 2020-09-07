package com.divinkas.app.words.room.word

import androidx.lifecycle.LiveData
import com.divinkas.app.words.bean.entities.Word

interface WordRepository {
    fun insert(word: String, translate: String, kodCategory: Int)
    fun loadAllWords(): LiveData<List<Word>>
    fun update(word: Word)
    fun delete(id: Int)
}
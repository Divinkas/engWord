package com.divinkas.app.words.room.repository

import androidx.lifecycle.LiveData
import com.divinkas.app.words.bean.entities.Word
import com.divinkas.app.words.room.db.EngWordsDB
import java.util.*

class WordRepository(
    private val db: EngWordsDB
) {

    fun insert(word: Word) {
        db.databaseWriteExecutor.execute {
            db.wordDao().insert(word)
        }
    }

    fun insert(word: String, translate: String, kodCategory: Int) {
        insert(createWord(word, translate, kodCategory))
    }

    fun loadAllWords(): LiveData<List<Word>> {
        return db.wordDao().getBaseWords()
    }

    private fun createWord(word: String, translate: String, kodCategory: Int): Word {
        return Word(
            word = word,
            translate = translate,
            kodCategory = kodCategory,
            dateAdding = Date().toString(),
            lastVisibleDate = Date().toString(),
            isLearned = false,
            rightAnswersCount = 0,
            wrongAnswersCount = 0
        )
    }
}
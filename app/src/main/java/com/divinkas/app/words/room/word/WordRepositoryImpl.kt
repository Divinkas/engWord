package com.divinkas.app.words.room.word

import androidx.lifecycle.LiveData
import com.divinkas.app.words.bean.entities.Word
import com.divinkas.app.words.room.EngWordsDB
import java.util.*

class WordRepositoryImpl(
    private val db: EngWordsDB
): WordRepository {

    override fun insert(word: String, translate: String, kodCategory: Int) {
        db.databaseWriteExecutor.execute {
            db.wordDao().insert(createWord(word, translate, kodCategory))
        }
    }

    override fun loadAllWords(): LiveData<List<Word>> {
        return db.wordDao().getBaseWords()
    }

    override fun update(word: Word) {
        // todo ----->
    }

    override fun delete(id: Int) {
        // todo ----->
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
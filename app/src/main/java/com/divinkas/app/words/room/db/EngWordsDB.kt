package com.divinkas.app.words.room.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.divinkas.app.words.bean.entities.Category
import com.divinkas.app.words.bean.entities.QuestionWord
import com.divinkas.app.words.bean.entities.Word
import com.divinkas.app.words.room.dao.ICategoryDao
import com.divinkas.app.words.room.dao.IWordsDao
import com.divinkas.app.words.utils.ConstRoom
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


@Database(
    entities = [Word::class, Category::class, QuestionWord::class],
    version = ConstRoom.DB_VERSION,
    exportSchema = false
)
abstract class EngWordsDB : RoomDatabase() {
    abstract fun wordDao(): IWordsDao
    abstract fun categoryDao(): ICategoryDao

    val databaseWriteExecutor: ExecutorService = Executors.newFixedThreadPool(4)

    companion object {
        @Volatile
        private var INSTANCE: EngWordsDB? = null

        fun getDatabase(context: Context): EngWordsDB {
            val tempInstance =
                INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EngWordsDB::class.java,
                    ConstRoom.DB_NAME
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
package com.divinkas.app.words.ui.test

import androidx.lifecycle.viewModelScope
import com.divinkas.app.words.base.viewmodel.AbstractScreenViewModel
import com.divinkas.app.words.bean.entities.Word
import com.divinkas.app.words.room.word.WordRepository
import kotlinx.coroutines.launch
import org.koin.core.inject

class TestViewModel : AbstractScreenViewModel() {
    private val wordRepository: WordRepository by inject()

    fun loadNextQuestions4() = viewModelScope.launch {
    }

    fun loadNextQuestions2() = viewModelScope.launch {
    }

    fun answer(isRight: Boolean, word: Word) {
    }
}
package com.divinkas.app.words.ui.word

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.divinkas.app.words.base.viewmodel.AbstractScreenViewModel
import com.divinkas.app.words.domain.entities.Category
import com.divinkas.app.words.domain.entities.Word
import com.divinkas.app.words.domain.view.WordCategoryModel
import com.divinkas.app.words.room.repository.CategoryRepository
import com.divinkas.app.words.room.repository.WordRepository
import com.divinkas.app.words.utils.DataConverter
import kotlinx.coroutines.launch
import org.koin.core.inject

class WordViewModel : AbstractScreenViewModel() {
    var wordsLiveData: LiveData<List<Word>> = MutableLiveData<List<Word>>()
    var categoryLiveData: LiveData<List<Category>> = MutableLiveData<List<Category>>()

    var wordCategoryLiveData: LiveData<List<WordCategoryModel>> =
        MutableLiveData<List<WordCategoryModel>>()

    private val wordRepository: WordRepository by inject()
    private val categoryRepository: CategoryRepository by inject()

    fun insertWord(word: String, translate: String, kodCategory: Int) {
        wordRepository.insert(word, translate, kodCategory)
    }

    private fun observeWordCategoryLiveData() {
        wordsLiveData.observeForever {
            if (!categoryLiveData.value.isNullOrEmpty()
                && wordCategoryLiveData.value.isNullOrEmpty()
            ) {
                createData(it, categoryLiveData.value!!)
            } else {
                updateWordsInWordCategory(it)
            }
        }

        categoryLiveData.observeForever {
            if (!wordsLiveData.value.isNullOrEmpty() && wordCategoryLiveData.value.isNullOrEmpty()) {
                createData(wordsLiveData.value!!, it)
            } else {
                updateCategoriesInWordCategory(it)
            }
        }
    }

    fun loadWords() = viewModelScope.launch {
        wordsLiveData = wordRepository.loadAllWords()
    }

    fun loadCategories() = viewModelScope.launch {
        categoryLiveData = categoryRepository.loadCategories()
    }

    fun openWordManagerFragment() {
        navController?.navigate(WordFragmentDirections.actionWordFragmentToWordManagerFragment())
    }

    fun openCategoryManagerFragment() {
        navController?.navigate(
            WordManagerFragmentDirections.actionWordManagerFragmentToCategoryManagerFragment()
        )
    }

    fun loadData() = viewModelScope.launch {
        wordsLiveData = wordRepository.loadAllWords()
        categoryLiveData = categoryRepository.loadCategories()
        observeWordCategoryLiveData()
    }

    private fun createData(words: List<Word>, categories: List<Category>) {
        (wordCategoryLiveData as MutableLiveData).value =
            DataConverter.createWordCategory(words, categories)
    }

    private fun updateWordsInWordCategory(words: List<Word>) {
        if (!categoryLiveData.value.isNullOrEmpty()) {
            (wordCategoryLiveData as MutableLiveData).value =
                DataConverter.createWordCategory(words, categoryLiveData.value!!)
        }
    }

    private fun updateCategoriesInWordCategory(categories: List<Category>) {
        if (!wordsLiveData.value.isNullOrEmpty()) {
            (wordCategoryLiveData as MutableLiveData).value =
                DataConverter.createWordCategory(wordsLiveData.value!!, categories)
        }
    }
}
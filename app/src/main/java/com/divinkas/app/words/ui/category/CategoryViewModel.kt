package com.divinkas.app.words.ui.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.divinkas.app.words.base.viewmodel.AbstractScreenViewModel
import com.divinkas.app.words.bean.entities.Category
import com.divinkas.app.words.room.repository.CategoryRepository
import kotlinx.coroutines.launch
import org.koin.core.inject

class CategoryViewModel : AbstractScreenViewModel() {
    private val categoryRepository: CategoryRepository by inject()

    var categoryLiveData: LiveData<List<Category>> = MutableLiveData<List<Category>>()

    fun loadCategories() = viewModelScope.launch {
        categoryLiveData = categoryRepository.loadCategories()
    }

    fun insertCategoryAsync(nameCategory: String) {
        categoryRepository.insert(Category(categoryName = nameCategory))
    }
}

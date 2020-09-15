package com.divinkas.app.words.ui.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.divinkas.app.words.base.viewmodel.AbstractScreenViewModel
import com.divinkas.app.words.bean.entities.Category
import com.divinkas.app.words.room.category.CategoryRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.koin.core.inject

class CategoryViewModel : AbstractScreenViewModel() {
    private val categoryRepository: CategoryRepository by inject()

    var categoryLiveData: LiveData<List<Category>> = MutableLiveData<List<Category>>()

    fun loadCategories() = viewModelScope.launch {
        categoryLiveData = categoryRepository.loadAllCategories()
    }

    fun addCategory(nameCategory: String) {
        viewModelScope.async {
            categoryRepository.insert(Category(categoryName = nameCategory))
        }
    }
}

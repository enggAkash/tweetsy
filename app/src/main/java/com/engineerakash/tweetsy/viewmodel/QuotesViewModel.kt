package com.engineerakash.tweetsy.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.engineerakash.tweetsy.api.Quote
import com.engineerakash.tweetsy.api.Resource
import com.engineerakash.tweetsy.repository.QuoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuotesViewModel @Inject constructor(val repository: QuoteRepository) : ViewModel() {

    private var _categories = MutableStateFlow<Resource<List<String>>>(
        Resource.Loading
    )
    val categories: StateFlow<Resource<List<String>>> = _categories

    private var _categorySpecificQuote = MutableStateFlow<Resource<List<Quote>>>(
        Resource.Loading
    )

    val categorySpecificQuote: StateFlow<Resource<List<Quote>>> = _categorySpecificQuote

    fun getCategories() {
        viewModelScope.launch {
            repository.getCategories().collect {
                _categories.value = it
            }
        }
    }

    fun getQuotes(categoryName: String) {
        viewModelScope.launch {
            repository.getQuotes(categoryName).collect {
                _categorySpecificQuote.value = it
            }
        }
    }
}
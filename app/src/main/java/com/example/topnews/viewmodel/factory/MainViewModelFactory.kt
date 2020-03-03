package com.example.topnews.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.topnews.repository.NewsRepository
import com.example.topnews.viewmodel.MainViewModel

class MainViewModelFactory(private val newsRepository: NewsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(newsRepository) as T
    }
}
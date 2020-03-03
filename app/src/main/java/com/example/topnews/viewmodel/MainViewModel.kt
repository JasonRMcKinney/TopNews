package com.example.topnews.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.topnews.model.Article
import com.example.topnews.repository.NewsRepository
import io.reactivex.disposables.CompositeDisposable
import java.net.UnknownHostException

class MainViewModel constructor(private val newsRepository: NewsRepository) : ViewModel() {
    private val disposable = CompositeDisposable()
    val state = MutableLiveData<AppState>()
    var loaded = false

    fun getTopNews() {
        state.value = AppState.LOADING
        disposable.add(
            newsRepository.getTopNews()
                .subscribe({
                    loaded = true
                    if (it.articles.isEmpty()) {
                        state.value = AppState.ERROR("No Articles Retrieved")
                    } else {
                        state.value = AppState.SUCCESS(it.articles)
                    }
                }, {
                    loaded = true
                    //errors
                    val errorString = when (it) {
                        is UnknownHostException -> "No Internet Connection"
                        else -> it.localizedMessage
                    }
                    state.value = AppState.ERROR(errorString)
                })
        )
    }

    sealed class AppState {
        object LOADING : AppState()
        data class SUCCESS(val articleList: List<Article>) : AppState()
        data class ERROR(val message: String) : AppState()
    }
}
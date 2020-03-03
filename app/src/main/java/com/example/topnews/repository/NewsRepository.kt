package com.example.topnews.repository

import com.example.topnews.model.NewsResponse
import io.reactivex.Single

interface NewsRepository {
    fun getTopNews(): Single<NewsResponse>
}
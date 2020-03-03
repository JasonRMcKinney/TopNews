package com.example.topnews.repository

import com.example.topnews.model.NewsResponse
import com.example.topnews.network.WebService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

open class NewsRepositoryImpl(private val webService: WebService) : NewsRepository {
    override fun getTopNews(): Single<NewsResponse> {
        return webService.getTopNews()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
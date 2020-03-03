package com.example.topnews.ui.listener

import com.example.topnews.model.Article

interface NewsItemClickListener {
    fun onClick(article: Article)
}
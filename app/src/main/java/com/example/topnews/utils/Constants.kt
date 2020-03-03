package com.example.topnews.utils

class Constants {
    companion object {
        private const val apiKey = "12d5cf1a4c4d4fbeb385d6ac2a4e4e83"
        const val baseUrl = "https://newsapi.org/"
        const val endpoint = "v2/top-headlines?country=us&apiKey=$apiKey"
    }
}
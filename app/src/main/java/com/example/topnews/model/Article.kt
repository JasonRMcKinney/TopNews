package com.example.topnews.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Article(

    @SerializedName("source")
    @Expose
    val source: Source,

    @SerializedName("author")
    @Expose
    val author: String,

    @SerializedName("title")
    @Expose
    val title: String,

    @SerializedName("desc")
    @Expose
    val desc: String,

    @SerializedName("urlToImage")
    @Expose
    val image: String,

    @SerializedName("url")
    @Expose
    val url: String
) : Parcelable
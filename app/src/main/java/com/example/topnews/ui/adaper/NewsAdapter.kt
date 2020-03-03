package com.example.topnews.ui.adaper

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.topnews.R
import com.example.topnews.model.Article
import com.example.topnews.ui.listener.NewsItemClickListener

class NewsAdapter(
    val articles: MutableList<Article>,
    private val newsClickLiistener: NewsItemClickListener
) :
    RecyclerView.Adapter<NewsViewHolder>() {

    override fun getItemCount() = articles.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bindItem(articles[position])
        holder.itemView.setOnClickListener {
            newsClickLiistener.onClick(articles[holder.adapterPosition])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder =
        NewsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_article,
                parent,
                false
            )
        )


}
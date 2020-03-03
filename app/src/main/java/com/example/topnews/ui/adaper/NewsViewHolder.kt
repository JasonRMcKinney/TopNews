package com.example.topnews.ui.adaper

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.topnews.model.Article
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_article.view.*

class NewsViewHolder(item: View) : RecyclerView.ViewHolder(item) {

    fun bindItem(article: Article) {
        //populate text
        itemView.articleTitle.text = article.title
        itemView.articleSource.text = article.source.name
        itemView.articleAuthor.text = article.author
        //load image
        Picasso.get().load(article.image).into(itemView.articleImage)
    }
}
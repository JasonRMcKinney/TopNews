package com.example.topnews.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.topnews.R
import com.example.topnews.model.Article
import com.example.topnews.network.WebService
import com.example.topnews.repository.NewsRepositoryImpl
import com.example.topnews.ui.adaper.NewsAdapter
import com.example.topnews.ui.listener.NewsItemClickListener
import com.example.topnews.viewmodel.MainViewModel
import com.example.topnews.viewmodel.factory.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    lateinit var newsAdapter: NewsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()

        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(NewsRepositoryImpl(WebService.instance))
        ).get(MainViewModel::class.java)

        viewModel.state.observe(this, Observer { appState ->
            when (appState) {
                is MainViewModel.AppState.LOADING -> displayLoading()
                is MainViewModel.AppState.SUCCESS -> displayNews(appState.articleList)
                is MainViewModel.AppState.ERROR -> displayMessage(appState.message)
                else -> displayMessage("Something Went Wrong... Try Again.")
            }
        })

        if (!viewModel.loaded) {
            viewModel.getTopNews()
        }

        retryButton.setOnClickListener {
            viewModel.getTopNews()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_refresh -> {
                viewModel.getTopNews()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun displayLoading() {
        // set correct visible element
        progressBar.visibility = View.VISIBLE
        rvNews.visibility = View.GONE
        containerMessage.visibility = View.GONE
    }

    private fun displayMessage(message: String) {
        // set correct visible element
        progressBar.visibility = View.GONE
        rvNews.visibility = View.GONE
        containerMessage.visibility = View.VISIBLE
        //set message
        messageText.text = message
    }

    private fun displayNews(articles: List<Article>) {
        // set recycler to eliminate flicker
        newsAdapter.articles.clear()
        newsAdapter.articles.addAll(articles)
        newsAdapter.notifyDataSetChanged()

        // set correct visible element
        progressBar.visibility = View.GONE
        rvNews.visibility = View.VISIBLE
        containerMessage.visibility = View.GONE
    }

    private fun initRecyclerView() {
        rvNews.layoutManager = LinearLayoutManager(this)
        newsAdapter = NewsAdapter(mutableListOf(), newsClickListener)
        rvNews.adapter = newsAdapter
    }

    private val newsClickListener = object : NewsItemClickListener {
        override fun onClick(article: Article) {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(article.url)
            startActivity(intent)
        }


    }
}

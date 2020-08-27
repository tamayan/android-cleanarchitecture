package com.example.cleanarchitecture.ui.news

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.databinding.ActivityNewsBinding
import com.example.cleanarchitecture.ui.news.list.NewsListAdapter
import com.example.cleanarchitecture.ui.news.list.NewsListViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


class NewsActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModel: NewsListViewModel

    private val binding: ActivityNewsBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_news)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.newsList.adapter = NewsListAdapter(viewModel, this)

        viewModel.items.observe(this, {
            (binding.newsList.adapter as NewsListAdapter).submitList(it)
        })
    }
}

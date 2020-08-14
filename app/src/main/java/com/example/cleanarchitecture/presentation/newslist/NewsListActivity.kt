package com.example.cleanarchitecture.presentation.newslist

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.databinding.ActivityNewsListBinding
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_news_list.*
import javax.inject.Inject


class NewsListActivity : DaggerAppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {

    @Inject
    lateinit var newsListViewModel: NewsListViewModel

    private val binding: ActivityNewsListBinding by lazy {
        DataBindingUtil
                .setContentView<ActivityNewsListBinding>(this, R.layout.activity_news_list)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)
        binding.swipeRefreshLayout.setOnRefreshListener(this)
        binding.viewModel = newsListViewModel
        newsListViewModel.isLoading.observe(this, Observer<Boolean> {
            swipeRefreshLayout.isRefreshing = it as Boolean
        })
        newsListViewModel.load()
    }

    override fun onRefresh() {
        newsListViewModel.load()
    }
}

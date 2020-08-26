package com.example.cleanarchitecture.ui.news.list

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.databinding.ActivityNewsListBinding
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


class NewsListActivity : DaggerAppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {

    @Inject
    lateinit var viewModel: NewsListViewModel

    private val binding: ActivityNewsListBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_news_list)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.swipeRefreshLayout.setOnRefreshListener(this)
        binding.newsList.adapter = NewsListAdapter(viewModel)

        viewModel.items.observe(this, {
            (binding.newsList.adapter as NewsListAdapter).submitList(it)
        })
    }

    override fun onRefresh() {
    }
}

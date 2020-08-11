package com.example.cleanarchitecture.presentation.ui.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.presentation.entity.NewsViewEntity
import com.example.cleanarchitecture.presentation.mvp.presenter.NewsListPresenter
import com.example.cleanarchitecture.presentation.mvp.view.NewsListView
import com.example.cleanarchitecture.presentation.myApplication
import com.example.cleanarchitecture.presentation.ui.adapter.NewsListAdapter
import com.jakewharton.rxbinding2.support.v4.widget.RxSwipeRefreshLayout
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_news_list.*
import javax.inject.Inject


class NewsListActivity : Activity(), NewsListView {

    @Inject
    lateinit var presenter: NewsListPresenter

    private val adapter: NewsListAdapter by lazy {
        NewsListAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)
        inject()
        setup()
        presenter.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }

    override fun showNewsList(list: List<NewsViewEntity>) {
        adapter.update(list)
        // Done Update
        swipeRefreshLayout.isRefreshing = false
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    @SuppressLint("ShowToast")
    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG)
    }

    override fun newsListRefresh(): Observable<Unit> {
        return RxSwipeRefreshLayout
                .refreshes(swipeRefreshLayout)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map { Unit }
    }

    private fun inject() {
        // DI
        myApplication
                .applicationComponent
//                .activityComponent()
//                .newsListActivityComponent(NewsListActivityModule(this))
                .inject(this)
    }

    private fun setup() {
        newsListRecyclerView.layoutManager = LinearLayoutManager(this)
        newsListRecyclerView.adapter = adapter
    }
}

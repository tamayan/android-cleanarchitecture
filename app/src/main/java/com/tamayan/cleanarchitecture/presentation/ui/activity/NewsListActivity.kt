package com.tamayan.cleanarchitecture.presentation.ui.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.jakewharton.rxbinding2.support.v4.widget.RxSwipeRefreshLayout
import com.tamayan.cleanarchitecture.R
import com.tamayan.cleanarchitecture.di.module.NewsListActivityModule
import com.tamayan.cleanarchitecture.presentation.entity.NewsViewEntity
import com.tamayan.cleanarchitecture.presentation.mvp.presenter.NewsListPresenter
import com.tamayan.cleanarchitecture.presentation.mvp.view.NewsListView
import com.tamayan.cleanarchitecture.presentation.myApplication
import com.tamayan.cleanarchitecture.presentation.ui.adapter.NewsListAdapter
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
                .activityComponent
                .newsListActivityComponent(NewsListActivityModule(this))
                .inject(this)
    }

    private fun setup() {
        newsListRecyclerView.layoutManager = LinearLayoutManager(this)
        newsListRecyclerView.adapter = adapter

        presenter.start()
    }
}

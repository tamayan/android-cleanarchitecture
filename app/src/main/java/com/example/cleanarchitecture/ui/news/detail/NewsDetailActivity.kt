package com.example.cleanarchitecture.ui.news.detail

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.cleanarchitecture.R
import kotlinx.android.synthetic.main.activity_news_detail.*

/**
 * Created by MSnowRobin016 on 2017/12/27.
 */

class NewsDetailActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)

        id_text.text = intent.getIntExtra(KEY_ID, 0).toString()
        title_text.text = intent.getStringExtra(KEY_TITLE)
    }

    companion object {

        private const val KEY_ID = "id"

        private const val KEY_TITLE = "title"

        fun callIntent(context: Context, id: Int, title: String): Intent {
            val intent = Intent(context, NewsDetailActivity::class.java)
            intent.putExtra(KEY_ID, id)
            intent.putExtra(KEY_TITLE, title)
            return intent
        }
    }
}
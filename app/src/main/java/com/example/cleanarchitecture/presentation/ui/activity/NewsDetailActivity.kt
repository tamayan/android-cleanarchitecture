package com.example.cleanarchitecture.presentation.ui.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.cleanarchitecture.R
import kotlinx.android.synthetic.main.list_item_news.*

/**
 * Created by MSnowRobin016 on 2017/12/27.
 */

class NewsDetailActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)

        idTextView.text = intent.getIntExtra(KEY_ID, 0).toString()
        titleTextView.text = intent.getStringExtra(KEY_TITLE)
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
package com.example.cleanarchitecture.ui.news

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cleanarchitecture.R


class NewsActivity : AppCompatActivity() {

//    private val binding: ActivityNewsBinding by lazy {
//        DataBindingUtil.setContentView(this, R.layout.activity_news)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
    }
}

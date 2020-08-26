package com.example.cleanarchitecture.ui.news.list

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanarchitecture.usecase.news.list.NewsListModel

@BindingAdapter(value = ["app:items"])
fun setItems(listView: RecyclerView, items: List<NewsListModel>?) {
    items?.let {
        (listView.adapter as NewsListAdapter).submitList(items)
    }
}
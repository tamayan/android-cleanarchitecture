package com.example.cleanarchitecture.feature.ui.newslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanarchitecture.databinding.ListItemNewsBinding
import com.example.cleanarchitecture.feature.ui.newslist.NewsListAdapter.BindViewHolder

class NewsListAdapter : RecyclerView.Adapter<BindViewHolder>() {

    private val list = ArrayList<NewsViewEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItemNewsBinding = ListItemNewsBinding.inflate(layoutInflater, parent, false)
        return BindViewHolder(listItemNewsBinding)
    }

    override fun onBindViewHolder(viewHolder: BindViewHolder, position: Int) {
        viewHolder.listItemNewsBinding.news = list[position]
    }

    override fun getItemCount(): Int = list.size

    fun update(list: List<NewsViewEntity>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    class BindViewHolder(val listItemNewsBinding: ListItemNewsBinding) :
            RecyclerView.ViewHolder(listItemNewsBinding.root)
}
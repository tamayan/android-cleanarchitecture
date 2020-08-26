package com.example.cleanarchitecture.ui.news.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanarchitecture.databinding.ListItemNewsBinding
import com.example.cleanarchitecture.ui.news.list.NewsListAdapter.ViewHolder
import com.example.cleanarchitecture.usecase.news.list.NewsListModel

class NewsListAdapter(private val viewModel: NewsListViewModel) : ListAdapter<NewsListModel, ViewHolder>(NewsDiffCallback()) {

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) =
            viewHolder.bind(viewModel, getItem(position))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder.from(parent)

    class ViewHolder private constructor(private val binding: ListItemNewsBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: NewsListViewModel, item: NewsListModel) {
            binding.news = item
            binding.viewModel = viewModel
        }

        companion object {

            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemNewsBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class NewsDiffCallback : DiffUtil.ItemCallback<NewsListModel>() {

    override fun areItemsTheSame(oldItem: NewsListModel, newItem: NewsListModel): Boolean =
            oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: NewsListModel, newItem: NewsListModel): Boolean =
            oldItem == newItem
}

@BindingAdapter(value = ["app:items"])
fun setItems(recyclerView: RecyclerView, items: List<NewsListModel>?) {
    items?.let {
        (recyclerView.adapter as NewsListAdapter).submitList(items)
    }
}
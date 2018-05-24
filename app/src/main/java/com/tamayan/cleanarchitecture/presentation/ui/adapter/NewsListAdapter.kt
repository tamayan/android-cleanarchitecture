package com.tamayan.cleanarchitecture.presentation.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tamayan.cleanarchitecture.R
import com.tamayan.cleanarchitecture.presentation.entity.NewsViewEntity
import com.tamayan.cleanarchitecture.presentation.ui.activity.NewsDetailActivity
import kotlinx.android.synthetic.main.list_item_news.view.*

/**
 * Created by MSnowRobin016 on 2017/12/27.
 */

class NewsListAdapter(private val context: Context) : RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {

    private val list = ArrayList<NewsViewEntity>()

    private val layoutInflater: LayoutInflater by lazy {
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.list_item_news, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.update(item)
        holder.itemView.setOnClickListener {
            context.startActivity(NewsDetailActivity.callIntent(context, item.id, item.title))
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun update(list: List<NewsViewEntity>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun update(entity: NewsViewEntity) {
            itemView.idTextView.text = entity.id.toString()
            itemView.titleTextView.text = entity.title
        }
    }
}
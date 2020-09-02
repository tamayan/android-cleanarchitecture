package com.example.cleanarchitecture.ui.video.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanarchitecture.databinding.ListItemVideoBinding
import com.example.cleanarchitecture.ui.video.list.VideosAdapter.ViewHolder
import com.example.cleanarchitecture.usecase.video.list.VideoModel

class VideosAdapter(private val viewModel: VideosViewModel) :
        ListAdapter<VideoModel, ViewHolder>(VideoDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder.from(parent)

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) =
            viewHolder.bind(viewModel, getItem(position))

    class ViewHolder private constructor(private val binding: ListItemVideoBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: VideosViewModel, video: VideoModel) {
            binding.apply {
                this.viewModel = viewModel
                this.video = video
                executePendingBindings()
            }
        }

        companion object {

            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemVideoBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

object VideoDiffCallback : DiffUtil.ItemCallback<VideoModel>() {

    override fun areItemsTheSame(oldItem: VideoModel, newItem: VideoModel): Boolean =
            oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: VideoModel, newItem: VideoModel): Boolean =
            oldItem == newItem
}

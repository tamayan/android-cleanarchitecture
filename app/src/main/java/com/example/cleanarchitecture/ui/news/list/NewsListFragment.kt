package com.example.cleanarchitecture.ui.news.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cleanarchitecture.databinding.FragmentNewsListBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class NewsListFragment : DaggerFragment() {

    @Inject
    lateinit var viewModel: NewsListViewModel

    private lateinit var binding: FragmentNewsListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNewsListBinding.inflate(inflater, container, false).apply {
            viewModel = viewModel
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.newsList.adapter = NewsListAdapter(viewModel, viewLifecycleOwner)

        viewModel.items.observe(viewLifecycleOwner, {
            (binding.newsList.adapter as NewsListAdapter).submitList(it)
        })
    }
}
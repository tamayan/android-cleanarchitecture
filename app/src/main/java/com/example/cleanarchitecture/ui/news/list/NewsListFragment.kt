package com.example.cleanarchitecture.ui.news.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.databinding.FragmentNewsListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsListFragment : Fragment(R.layout.fragment_news_list) {

    private lateinit var binding: FragmentNewsListBinding

    private val viewModel: NewsListViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNewsListBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.newsList.adapter = NewsListAdapter(viewModel, viewLifecycleOwner)

        viewModel.items.observe(viewLifecycleOwner, {
            (binding.newsList.adapter as NewsListAdapter).submitList(it)
        })

        viewModel.clickId.observe(viewLifecycleOwner, {
            val action = NewsListFragmentDirections.actionNewsListToNewsDetail(it)
            findNavController().navigate(action)
        })
    }
}
package com.example.cleanarchitecture.ui.news.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.databinding.FragmentNewsDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDetailFragment : Fragment(R.layout.fragment_news_detail) {

    private lateinit var binding: FragmentNewsDetailBinding

    private val args: NewsDetailFragmentArgs by navArgs()

    private val viewModel: NewsDetailViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNewsDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.find(args.id)
        viewModel.newsDetail.observe(viewLifecycleOwner, {
            binding.newsDetail = it
        })
    }
}
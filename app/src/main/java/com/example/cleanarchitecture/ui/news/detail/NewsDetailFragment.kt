package com.example.cleanarchitecture.ui.news.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.databinding.FragmentNewsDetailBinding

class NewsDetailFragment : Fragment(R.layout.fragment_news_detail) {

    private val args: NewsDetailFragmentArgs by navArgs()

    private lateinit var binding: FragmentNewsDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNewsDetailBinding.inflate(inflater, container, false)
        return binding.root
    }
}
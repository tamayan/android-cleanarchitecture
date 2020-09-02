package com.example.cleanarchitecture.ui.video.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.databinding.FragmentVideoInfoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VideoInfoFragment : Fragment(R.layout.fragment_video_info) {

    private lateinit var binding: FragmentVideoInfoBinding

    private val args: VideoInfoFragmentArgs by navArgs()

    private val viewModel: VideoInfoViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentVideoInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        setupVideo()
    }

    private fun setupVideo() {
        viewModel.find(args.id)
        viewModel.video.observe(viewLifecycleOwner, {
            binding.video = it
        })
    }
}
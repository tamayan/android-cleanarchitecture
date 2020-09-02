package com.example.cleanarchitecture.ui.video.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.databinding.FragmentVideosBinding
import com.example.cleanarchitecture.ui.EventObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VideosFragment : Fragment(R.layout.fragment_videos) {

    private lateinit var binding: FragmentVideosBinding

    private val viewModel: VideosViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentVideosBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        setupListAdapter()
        setupNavigation()
    }

    private fun setupListAdapter() {
        binding.videoList.adapter = VideosAdapter(viewModel)
        viewModel.items.observe(viewLifecycleOwner, {
            (binding.videoList.adapter as VideosAdapter).submitList(it)
        })
    }

    private fun setupNavigation() {
        viewModel.clickId.observe(viewLifecycleOwner, EventObserver {
            val action = VideosFragmentDirections.actionVideosToVideoInfo(it)
            findNavController().navigate(action)
        })
    }
}
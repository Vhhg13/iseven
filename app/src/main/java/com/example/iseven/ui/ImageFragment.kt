package com.example.iseven.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.iseven.R
import com.example.iseven.databinding.FragmentImageBinding
import com.example.iseven.ui.viewmodels.ImageFragmentViewModel
import com.example.iseven.util.ImagesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageFragment : Fragment() {
    private lateinit var binding: FragmentImageBinding
    private val viewModel: ImageFragmentViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.uiState.observe(viewLifecycleOwner){
            binding.image.setImageBitmap(it)
            viewModel.uiState.value?.let { viewModel.save(it) }
        }
        binding.search.setOnClickListener {
            viewModel.load(binding.link.text.toString())
        }

        binding.images.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val imagesAdapter = ImagesAdapter()
        viewModel.images.observe(viewLifecycleOwner){
            imagesAdapter.submitList(it)
        }
        binding.images.adapter = imagesAdapter

        binding.clearImages.setOnClickListener {
            viewModel.clearImages()
        }
    }
}
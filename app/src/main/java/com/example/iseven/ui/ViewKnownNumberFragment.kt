package com.example.iseven.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.iseven.R
import com.example.iseven.databinding.FragmentViewKnownNumberBinding
import com.example.iseven.util.AdFormatter

class ViewKnownNumberFragment : Fragment() {
    private lateinit var binding: FragmentViewKnownNumberBinding
    private val viewModel: ViewKnownNumberFragmentViewModel by viewModels{ ViewKnownNumberFragmentViewModel.Factory }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentViewKnownNumberBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.uiState.observe(viewLifecycleOwner){
            binding.number.text = it.number.toString()
            binding.parity.text = resources.getString(if(it.isEven) R.string.even else R.string.odd)
            binding.ad.text = AdFormatter.format(it.ad)
        }
        viewModel.check(ViewKnownNumberFragmentArgs.fromBundle(requireArguments()).number.number)
    }
}
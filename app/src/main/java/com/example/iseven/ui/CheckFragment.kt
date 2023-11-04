package com.example.iseven.ui

import android.os.Bundle
import android.os.IBinder
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.iseven.R
import com.example.iseven.databinding.FragmentCheckBinding
import com.example.iseven.util.AdFormatter

class CheckFragment : Fragment() {

    private lateinit var binding: FragmentCheckBinding
    val viewModel: CheckFragmentViewModel by viewModels { CheckFragmentViewModel.Factory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // inflater.inflate(R.layout.fragment_check, container, false)
        binding = FragmentCheckBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = view.findNavController()
        binding.submitButton.setOnClickListener {
            viewModel.check(binding.numberToBeChecked.text.toString().toInt())
            requireActivity().getSystemService(InputMethodManager::class.java).hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }
        binding.seeKnown.setOnClickListener {
            navController.navigate(R.id.action_checkFragment_to_knownNumbersFragment)
        }
        viewModel.uiState.observe(viewLifecycleOwner){
            binding.result.text = if(it.isEven) "Четное" else "Нечётное"

            binding.ad.text = AdFormatter.format(it.ad)
        }
        binding.numberToBeChecked.setOnEditorActionListener { _, _, _ ->
            viewModel.check(binding.numberToBeChecked.text.toString().toInt())
            requireActivity().getSystemService(InputMethodManager::class.java).hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }
}
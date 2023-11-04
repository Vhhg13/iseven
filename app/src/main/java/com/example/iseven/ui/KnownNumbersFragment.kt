package com.example.iseven.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import com.example.iseven.data.model.KnownListItem
import com.example.iseven.databinding.FragmentKnownNumbersBinding
import com.example.iseven.util.KnownListAdapter

class KnownNumbersFragment : Fragment(), KnownListAdapter.KnownNumberPressedListener {
    private lateinit var binding: FragmentKnownNumbersBinding
    val viewModel: KnownNumbersFragmentViewModel by viewModels { KnownNumbersFragmentViewModel.Factory }
//    private lateinit var adapter: ListAdapter<KnownListItem, KnownListAdapter.KnownViewHolder>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentKnownNumbersBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = KnownListAdapter(this)
        viewModel.uiState.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
        //adapter.submitList(listOf(KnownListItem(12, true), KnownListItem(13, false)))
        binding.recycler.layoutManager = LinearLayoutManager(activity)
        binding.recycler.adapter = adapter
    }

    override fun onItemClick(item: KnownListItem) {
        val navController = findNavController()
        val action = KnownNumbersFragmentDirections.actionKnownNumbersFragmentToViewKnownNumberFragment(item)
        navController.navigate(action)
    }

    override fun removeItem(item: KnownListItem) {
        viewModel.remove(item)
    }
}
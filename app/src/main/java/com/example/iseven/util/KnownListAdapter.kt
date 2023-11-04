package com.example.iseven.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.iseven.R
import com.example.iseven.data.model.KnownListItem
import com.example.iseven.databinding.KnownNumberItemBinding

class KnownListAdapter(private val fragment: KnownNumberPressedListener): ListAdapter<KnownListItem, KnownListAdapter.KnownViewHolder>(DIFF) {
    interface KnownNumberPressedListener{
        fun onItemClick(item: KnownListItem)
        fun removeItem(item: KnownListItem)
    }
    class KnownViewHolder(private val binding: KnownNumberItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(knownListItem: KnownListItem, fragment: KnownNumberPressedListener) {
            binding.apply {
                checkedNumber.text = knownListItem.number.toString()
                parity.text = root.context.getString(if(knownListItem.parity) R.string.even else R.string.odd)
                root.setOnClickListener{
                    fragment.onItemClick(knownListItem)
                }
                binding.x.setOnClickListener{
                    fragment.removeItem(knownListItem)
                }
            }
        }
    }

    companion object DIFF : DiffUtil.ItemCallback<KnownListItem>() {
        override fun areItemsTheSame(oldItem: KnownListItem, newItem: KnownListItem) = oldItem.number == newItem.number

        override fun areContentsTheSame(oldItem: KnownListItem, newItem: KnownListItem) = oldItem.number == newItem.number
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KnownViewHolder {
        val binding = KnownNumberItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return KnownViewHolder(binding)
    }

    override fun onBindViewHolder(holder: KnownViewHolder, position: Int) {
        holder.bind(getItem(position), fragment)
    }
}
package com.example.iseven.util

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.iseven.R
import com.example.iseven.databinding.ImageBinding

class ImagesAdapter: ListAdapter<Bitmap, ImagesAdapter.ImageItemViewHolder>(DIFF) {

    class ImageItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.image)
    }

    object DIFF : DiffUtil.ItemCallback<Bitmap>(){
        override fun areItemsTheSame(oldItem: Bitmap, newItem: Bitmap) = oldItem.sameAs(newItem)
        override fun areContentsTheSame(oldItem: Bitmap, newItem: Bitmap) = oldItem.sameAs(newItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageItemViewHolder {
        val view = ImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageItemViewHolder(view.root)
    }

    override fun onBindViewHolder(holder: ImageItemViewHolder, position: Int) {
        holder.imageView.setImageBitmap(getItem(position))
    }
}
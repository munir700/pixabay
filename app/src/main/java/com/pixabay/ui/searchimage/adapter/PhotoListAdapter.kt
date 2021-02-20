package com.pixabay.ui.searchimage.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.data.local.models.Photo
import com.pixabay.R
import com.pixabay.databinding.PhotoItemBinding
import com.pixabay.ui.base.adapter.BaseAdapter

class PhotoListAdapter (private val photoClickCallback: ((Photo) -> Unit)?) :
    BaseAdapter<Photo, PhotoItemBinding>(PhotosDiffCallback) {

    override fun bind(binding: PhotoItemBinding, item: Photo) {
        binding.photo = item
    }

    override fun createBinding(parent: ViewGroup): PhotoItemBinding {
        val binding = DataBindingUtil.inflate<PhotoItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.photo_item,
            parent,
            false
        )
        binding.root.setOnClickListener {
            binding.photo?.let {
                photoClickCallback?.invoke(it)
            }
        }
        return binding
    }


    companion object {
        val PhotosDiffCallback = object : DiffUtil.ItemCallback<Photo>() {
            override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
                return oldItem.id == newItem.id
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}
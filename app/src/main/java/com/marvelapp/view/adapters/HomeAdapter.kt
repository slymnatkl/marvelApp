package com.marvelapp.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.marvelapp.databinding.RowItemCharacterListBinding
import com.marvelapp.repository.model.Character

class HomeAdapter: PagingDataAdapter<Character, HomeAdapter.MovieViewHolder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = RowItemCharacterListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.binding.character = getItem(position)
    }

    class MovieViewHolder(val binding: RowItemCharacterListBinding): RecyclerView.ViewHolder(binding.root)

    class DiffUtilCallBack: DiffUtil.ItemCallback<Character>() {

        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return (oldItem.id == newItem.id) && (oldItem.name == newItem.name)
        }
    }
}
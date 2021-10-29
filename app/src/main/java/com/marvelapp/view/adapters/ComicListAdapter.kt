package com.marvelapp.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marvelapp.databinding.RowItemComicListBinding
import com.marvelapp.repository.model.Comic

class ComicListAdapter: RecyclerView.Adapter<ComicListAdapter.ComicViewHolder>() {

    var itemList: List<Comic> = ArrayList()

    fun setItems(itemList: List<Comic>){

        this@ComicListAdapter.itemList = itemList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        val binding = RowItemComicListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ComicViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {

        holder.binding.comic = itemList[position]
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class ComicViewHolder(val binding: RowItemComicListBinding): RecyclerView.ViewHolder(binding.root)
}
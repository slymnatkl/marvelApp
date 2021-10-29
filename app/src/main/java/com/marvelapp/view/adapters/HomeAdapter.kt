package com.marvelapp.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marvelapp.databinding.RowItemCharacterListBinding
import com.marvelapp.repository.model.Character

class HomeAdapter: RecyclerView.Adapter<HomeAdapter.MovieViewHolder>() {

    var characterList: List<Character> = ArrayList()

    fun setItems(characterList: List<Character>){

        this@HomeAdapter.characterList = characterList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = RowItemCharacterListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        holder.binding.character = characterList[position]
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    class MovieViewHolder(val binding: RowItemCharacterListBinding): RecyclerView.ViewHolder(binding.root)
}
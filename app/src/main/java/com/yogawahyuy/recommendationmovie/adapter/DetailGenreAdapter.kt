package com.yogawahyuy.recommendationmovie.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yogawahyuy.recommendationmovie.databinding.RvItemGenreBinding
import com.yogawahyuy.recommendationmovie.model.Cast

class DetailGenreAdapter(private val context: Context, private val genreList:List<String>) : RecyclerView.Adapter<DetailGenreAdapter.ViewHolder>(){

    inner class ViewHolder(val binding: RvItemGenreBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(genre:String){
            binding.tvGenreRv.text = genre
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvItemGenreBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = genreList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(genreList[position])
    }
}
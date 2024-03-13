package com.yogawahyuy.recommendationmovie.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.yogawahyuy.recommendationmovie.databinding.RvItemMainBinding
import com.yogawahyuy.recommendationmovie.util.BaseURL

class HomeRecyclerAdapter(private val contex:Context,private val urlImgList:List<String>): RecyclerView.Adapter<HomeRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(val binding:RvItemMainBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(url:String){
            Picasso.get().load(BaseURL().baseImageOri+url).into(binding.ivItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvItemMainBinding.inflate(LayoutInflater.from(contex),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = urlImgList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(urlImgList[position])
    }
}
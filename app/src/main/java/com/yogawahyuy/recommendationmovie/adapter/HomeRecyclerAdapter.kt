package com.yogawahyuy.recommendationmovie.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.yogawahyuy.recommendationmovie.databinding.RvItemMainBinding
import com.yogawahyuy.recommendationmovie.ui.DetailMovieActivity
import com.yogawahyuy.recommendationmovie.util.BaseURL

class HomeRecyclerAdapter(private val contex:Context,private val idList:List<Int>,private val urlImgList:List<String>): RecyclerView.Adapter<HomeRecyclerAdapter.ViewHolder>() {

    private lateinit var mListener: OnItemClickListener
    inner class ViewHolder(val binding:RvItemMainBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(url:String,id:Int){
            Picasso.get().load(BaseURL().baseImageOri+url).into(binding.ivItem)
            itemView.setOnClickListener {
                val intent = Intent(contex, DetailMovieActivity::class.java)
                intent.putExtra("id",id)
                contex.startActivity(intent)
            }
        }
    }
    fun setOnItemClickListener(listener: OnItemClickListener){
        mListener = listener
    }
    interface OnItemClickListener{
        fun onItemClick(position : Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvItemMainBinding.inflate(LayoutInflater.from(contex),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = urlImgList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(urlImgList[position],idList[position])
    }

}
package com.yogawahyuy.recommendationmovie.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.yogawahyuy.recommendationmovie.databinding.RvItemSearchBinding
import com.yogawahyuy.recommendationmovie.model.ResultTrending
import com.yogawahyuy.recommendationmovie.ui.DetailMovieActivity
import com.yogawahyuy.recommendationmovie.util.BaseURL

class SearchRecyclerAdapter(private val context: Context,private val resultList: List<ResultTrending>) : RecyclerView.Adapter<SearchRecyclerAdapter.ViewHolder>(){
    private lateinit var mListener: OnItemClickListener
    inner class ViewHolder(val binding:RvItemSearchBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(result:ResultTrending){
            Picasso.get().load(BaseURL().baseImageOri+result.posterPath).into(binding.ivItem)
            binding.tvNameMain.text =if (result.mediaType == "tv") result.name else result.title
            itemView.setOnClickListener {
                val intent = Intent(context, DetailMovieActivity::class.java)
                intent.putExtra("id",result.id)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvItemSearchBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = resultList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(resultList[position])
    }
    fun setOnItemClickListener(listener: OnItemClickListener){
        mListener = listener
    }
    interface OnItemClickListener{
        fun onItemClick(position : Int)
    }

}
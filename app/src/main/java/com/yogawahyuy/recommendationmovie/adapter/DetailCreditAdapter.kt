package com.yogawahyuy.recommendationmovie.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.yogawahyuy.recommendationmovie.databinding.RvItemCreditBinding
import com.yogawahyuy.recommendationmovie.model.Cast
import com.yogawahyuy.recommendationmovie.util.BaseURL

class DetailCreditAdapter(private val context:Context,private val castList:List<Cast>): RecyclerView.Adapter<DetailCreditAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: RvItemCreditBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(cast: Cast){
            Picasso.get().load(BaseURL().baseImageOri+cast.profilePath).into(binding.ivItemCredit)
            binding.tvNameCredit.text = cast.name
            binding.tvCharacterCredit.text = cast.character
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvItemCreditBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = castList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(castList[position])
    }
}
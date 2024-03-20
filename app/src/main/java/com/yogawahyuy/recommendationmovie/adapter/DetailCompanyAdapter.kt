package com.yogawahyuy.recommendationmovie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.yogawahyuy.recommendationmovie.R
import com.yogawahyuy.recommendationmovie.databinding.RvItemCreditBinding
import com.yogawahyuy.recommendationmovie.util.BaseURL

class DetailCompanyAdapter(private val nameComp:List<String>, private val urlPath:List<String>): RecyclerView.Adapter<DetailCompanyAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: RvItemCreditBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(name:String,path:String){
            if (path == "default")
                Picasso.get().load(R.mipmap.ic_launcher).into(binding.ivItemCredit)
            else Picasso.get().load(BaseURL().baseImageOri+path).into(binding.ivItemCredit)
            binding.tvNameCredit.text = name
            binding.tvCharacterCredit.visibility = View.GONE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvItemCreditBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = nameComp.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(nameComp[position],urlPath[position])
    }
}
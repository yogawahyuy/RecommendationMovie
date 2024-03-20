package com.yogawahyuy.recommendationmovie.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import com.yogawahyuy.recommendationmovie.R
import com.yogawahyuy.recommendationmovie.adapter.DetailCompanyAdapter
import com.yogawahyuy.recommendationmovie.adapter.DetailCreditAdapter
import com.yogawahyuy.recommendationmovie.adapter.DetailGenreAdapter
import com.yogawahyuy.recommendationmovie.databinding.ActivityDetailMovieBinding
import com.yogawahyuy.recommendationmovie.util.BaseURL
import com.yogawahyuy.recommendationmovie.viewmodel.DetailViewModel
import com.yogawahyuy.recommendationmovie.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

@AndroidEntryPoint
class DetailMovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailMovieBinding
    private lateinit var viewModel: DetailViewModel
    var df1 = DecimalFormat("#.##", DecimalFormatSymbols(Locale.US))
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
        WindowCompat.setDecorFitsSystemWindows(window,false)
        viewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        val intent = intent
        viewModel.loadDetail(intent.getIntExtra("id",0))
        getDetailMovie()
        getCreditMovie()
        Log.d("isiid", "onCreate: ${intent.getIntExtra("id",0)}")
    }

    private fun getDetailMovie(){
        val layMng = GridLayoutManager(this,2)
        val layMngProdComp = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.rvDetailGenre.layoutManager = layMng
        binding.rvDetailComp.layoutManager = layMngProdComp
        viewModel.observerDetailMovie().observe(this, Observer {
            Picasso.get().load(BaseURL().baseImageOri+it.posterPath).into(binding.detailPoster)
            Picasso.get().load(BaseURL().baseImageOri+it.backdropPath).into(binding.ivBackdrop)
            binding.titleMoviedetail.text = it.title
            binding.tvRating.text = df1.format(it.voteAverage)
            binding.tvValueOverview.text = it.overview
            binding.tvValueStatus.text = buildString {
                append(it.status)
                append(" At ")
                append(it.releaseDate)
            }
            binding.tvStatusBudget.text = convNumber(it.budget!!)
            binding.tvStatusRevenue.text = convNumber(it.revenue!!)
            val genreList = arrayListOf<String>()
            for (i in it.genres.indices) {
                genreList.add(it.genres[i].name!!)
            }
            val adapter = DetailGenreAdapter(this,genreList)
            binding.rvDetailGenre.adapter=adapter

            //prodcomp
            val nameList = arrayListOf<String>()
            val urlList = arrayListOf<String>()
            for (i in it.productionCompanies.indices){
                nameList.add(it.productionCompanies[i].name!!)
                if (it.productionCompanies[i].logoPath == null)
                    urlList.add("default")
                else urlList.add(it.productionCompanies[i].logoPath!!)
            }
            val adapterComp = DetailCompanyAdapter(nameList,urlList)
            binding.rvDetailComp.adapter = adapterComp
        })
    }
    private fun getCreditMovie(){
        val layMng = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        binding.rvDetailCast.layoutManager = layMng
        viewModel.observerCreditMovie().observe(this, Observer {
            val adapter =  DetailCreditAdapter(this,it)
            binding.rvDetailCast.adapter = adapter
        })
    }
    private fun convNumber(number:Int): String{
        val format = NumberFormat.getCurrencyInstance()
        format.maximumFractionDigits = 0
        format.currency = Currency.getInstance("USD")
        return format.format(number)
    }
}
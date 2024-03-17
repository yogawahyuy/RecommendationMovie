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
import com.squareup.picasso.Picasso
import com.yogawahyuy.recommendationmovie.R
import com.yogawahyuy.recommendationmovie.databinding.ActivityDetailMovieBinding
import com.yogawahyuy.recommendationmovie.util.BaseURL
import com.yogawahyuy.recommendationmovie.viewmodel.DetailViewModel
import com.yogawahyuy.recommendationmovie.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
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
        getDetailMovie(intent.getIntExtra("id",0))
        Log.d("isiid", "onCreate: ${intent.getIntExtra("id",0)}")
    }

    private fun getDetailMovie(movieId:Int){
        viewModel.loadDetail(movieId)
        viewModel.observerDetailMovie().observe(this, Observer {
            Picasso.get().load(BaseURL().baseImageOri+it.posterPath).into(binding.detailPoster)
            Picasso.get().load(BaseURL().baseImageOri+it.backdropPath).into(binding.ivBackdrop)
            binding.titleMoviedetail.text = it.title
            binding.tvRating.text = df1.format(it.voteAverage)
            binding.tvValueOverview.text = it.overview
        })
    }
}
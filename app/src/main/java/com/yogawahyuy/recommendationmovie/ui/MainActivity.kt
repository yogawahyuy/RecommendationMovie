package com.yogawahyuy.recommendationmovie.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.core.view.WindowCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.yogawahyuy.recommendationmovie.R
import com.yogawahyuy.recommendationmovie.databinding.ActivityMainBinding
import com.yogawahyuy.recommendationmovie.util.BaseURL
import com.yogawahyuy.recommendationmovie.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        EdgeToEdgeUtils.applyEdgeToEdge(this,true)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_frag_nav) as NavHostFragment
        navController = navHostFragment.navController

        binding.mainBottomnav.setupWithNavController(navController)

        WindowCompat.setDecorFitsSystemWindows(window,false)
    }


}
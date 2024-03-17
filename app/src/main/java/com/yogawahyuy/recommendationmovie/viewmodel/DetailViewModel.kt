package com.yogawahyuy.recommendationmovie.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yogawahyuy.recommendationmovie.model.DetailMovieModel
import com.yogawahyuy.recommendationmovie.util.repository.DetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(val repository: DetailRepository):ViewModel() {
    private val liveDataDetail : MutableLiveData<DetailMovieModel> = MutableLiveData()
    fun loadDetail(movieId:Int){
        repository.getDetailMovie(liveDataDetail,movieId)
    }
    fun observerDetailMovie(): MutableLiveData<DetailMovieModel> = liveDataDetail
}
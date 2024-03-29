package com.yogawahyuy.recommendationmovie.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yogawahyuy.recommendationmovie.model.Cast
import com.yogawahyuy.recommendationmovie.model.DetailMovieModel
import com.yogawahyuy.recommendationmovie.model.ResultVideos
import com.yogawahyuy.recommendationmovie.util.repository.DetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(val repository: DetailRepository):ViewModel() {
    private val liveDataDetail : MutableLiveData<DetailMovieModel> = MutableLiveData()
    private val liveDataCredit: MutableLiveData<List<Cast>> = MutableLiveData()
    private val liveDataVideos: MutableLiveData<List<ResultVideos>> = MutableLiveData()
    fun loadDetail(movieId:Int){
        repository.getDetailMovie(liveDataDetail,movieId)
        repository.getCreditMovie(liveDataCredit,movieId)
        repository.getVideosProvider(liveDataVideos,movieId)
    }
    fun observerDetailMovie(): MutableLiveData<DetailMovieModel> = liveDataDetail
    fun observerCreditMovie():MutableLiveData<List<Cast>> = liveDataCredit
    fun observerVideosProvider():MutableLiveData<List<ResultVideos>> = liveDataVideos
}
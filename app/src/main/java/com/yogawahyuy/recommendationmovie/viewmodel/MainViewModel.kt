package com.yogawahyuy.recommendationmovie.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yogawahyuy.recommendationmovie.model.Result
import com.yogawahyuy.recommendationmovie.model.ResultTrending
import com.yogawahyuy.recommendationmovie.util.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val repository: MainRepository): ViewModel() {

    private var liveDataNowPlaying :MutableLiveData<List<Result>> = MutableLiveData()
    private var liveDataUpcoming :MutableLiveData<List<Result>> = MutableLiveData()
    private var liveDataPopular:MutableLiveData<List<Result>> = MutableLiveData()
    private var liveDataTopRated:MutableLiveData<List<Result>> = MutableLiveData()

    //trending
    private var liveDataTrendingAll: MutableLiveData<List<ResultTrending>> = MutableLiveData()

    fun loadData(){
        repository.getApiNowPlaying(liveDataNowPlaying)
        repository.getApiUpcoming(liveDataUpcoming)
        repository.getApiPopular(liveDataPopular)
        repository.getApiTopRated(liveDataTopRated)
        repository.getApiTrendingAll(liveDataTrendingAll)
    }

    fun observerNP() : MutableLiveData<List<Result>> = liveDataNowPlaying
    fun observerUp() : MutableLiveData<List<Result>> = liveDataUpcoming
    fun observerPopular() : MutableLiveData<List<Result>> = liveDataPopular
    fun observerTopRated() : MutableLiveData<List<Result>> = liveDataTopRated

    fun observerTrendingAll():MutableLiveData<List<ResultTrending>> = liveDataTrendingAll
}
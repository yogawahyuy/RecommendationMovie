package com.yogawahyuy.recommendationmovie.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yogawahyuy.recommendationmovie.model.Result
import com.yogawahyuy.recommendationmovie.util.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val repository: MainRepository): ViewModel() {

    var liveDataNowPlaying :MutableLiveData<List<Result>> = MutableLiveData()
    var liveDataUpcoming :MutableLiveData<List<Result>> = MutableLiveData()

    fun loadData(){
        repository.getApiNowPlaying(liveDataNowPlaying)
        repository.getApiUpcoming(liveDataUpcoming)
    }

    fun observerNP() : MutableLiveData<List<Result>> = liveDataNowPlaying
    fun oberverUp() : MutableLiveData<List<Result>> = liveDataUpcoming
}
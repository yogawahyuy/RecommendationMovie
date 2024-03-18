package com.yogawahyuy.recommendationmovie.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yogawahyuy.recommendationmovie.model.Result
import com.yogawahyuy.recommendationmovie.model.ResultTrending
import com.yogawahyuy.recommendationmovie.util.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: SearchRepository):ViewModel() {
    private var liveDataSearch: MutableLiveData<List<ResultTrending>> = MutableLiveData()

    fun loadSearchResult(searchString:String){repository.getSearchResult(liveDataSearch,searchString)}

    fun observerSearchResult(): MutableLiveData<List<ResultTrending>> = liveDataSearch
}
package com.yogawahyuy.recommendationmovie.util

import com.yogawahyuy.recommendationmovie.model.MovieListModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import rx.Observable
import java.util.Objects

interface NetworkService {
    @GET("movie/upcoming")
    fun getUpcomingMovie():Observable<Response<MovieListModel>>

    @GET("movie/now_playing")
    fun getNowPlaying():Observable<Response<MovieListModel>>

}
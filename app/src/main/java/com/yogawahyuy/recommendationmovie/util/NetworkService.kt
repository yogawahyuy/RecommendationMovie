package com.yogawahyuy.recommendationmovie.util

import com.yogawahyuy.recommendationmovie.model.MovieListModel
import com.yogawahyuy.recommendationmovie.model.TrendingListModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable
import java.util.Objects

interface NetworkService {
    @GET("movie/upcoming")
    fun getUpcomingMovie():Observable<Response<MovieListModel>>

    @GET("movie/now_playing")
    fun getNowPlaying():Observable<Response<MovieListModel>>
    @GET("movie/popular")
    fun getPopular():Observable<Response<MovieListModel>>
    @GET("movie/top_rated")
    fun getTopRated():Observable<Response<MovieListModel>>
    @GET("trending/all/{time_window}")
    fun getTrendingAll(@Path("time_window") timeWindow:String):Observable<Response<TrendingListModel>>

}
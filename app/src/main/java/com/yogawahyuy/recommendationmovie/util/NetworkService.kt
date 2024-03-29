package com.yogawahyuy.recommendationmovie.util

import com.yogawahyuy.recommendationmovie.model.CreditMovieModel
import com.yogawahyuy.recommendationmovie.model.DetailMovieModel
import com.yogawahyuy.recommendationmovie.model.MovieListModel
import com.yogawahyuy.recommendationmovie.model.TrendingListModel
import com.yogawahyuy.recommendationmovie.model.VideosProviderModel
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

    //detail movie
    @GET("movie/{movie_id}")
    fun getDetailMovie(@Path("movie_id") movieId:Int):Observable<Response<DetailMovieModel>>
    @GET("movie/{movie_id}/credits")
    fun getCreditMovies(@Path("movie_id") movieId: Int):Observable<Response<CreditMovieModel>>
    @GET("movie/{movie_id}/videos")
    fun getVideoProvider(@Path("movie_id") movieId: Int): Observable<Response<VideosProviderModel>>

    //search
    @GET("search/multi")
    fun getSearchResult(@Query("query") searchString: String):Observable<Response<TrendingListModel>>

}
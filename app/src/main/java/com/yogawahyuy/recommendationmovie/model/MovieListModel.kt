package com.yogawahyuy.recommendationmovie.model

import com.google.gson.annotations.SerializedName

data class MovieListModel(
    @SerializedName("results")
    val result: List<Result>
)

data class Result(
    @SerializedName("adult")
    val adult:Boolean,
    @SerializedName("backdrop_path")
    val backdropPath:String,
    @SerializedName("id")
    val id:Int,
    @SerializedName("original_title")
    val originalTitle:String,
    @SerializedName("overview")
    val overView:String,
    @SerializedName("popularity")
    val popularity:Float,
    @SerializedName("poster_path")
    val posterPath:String,
    @SerializedName("release_date")
    val releaseDate:String,
    @SerializedName("title")
    val title:String,
    @SerializedName("vote_average")
    val voteAverage:Float,
    @SerializedName("vote_count")
    val voteCount:Int
)

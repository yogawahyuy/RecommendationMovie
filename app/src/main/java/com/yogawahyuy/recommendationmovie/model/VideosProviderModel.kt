package com.yogawahyuy.recommendationmovie.model

import com.google.gson.annotations.SerializedName

data class VideosProviderModel(
    @SerializedName("results")
    val resultVideos:List<ResultVideos>
)
data class ResultVideos(
    @SerializedName("name")
    val name:String,
    @SerializedName("key")
    val key:String,
    @SerializedName("type")
    val type:String,
    @SerializedName("site")
    val site:String,
)

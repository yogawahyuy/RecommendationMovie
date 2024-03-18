package com.yogawahyuy.recommendationmovie.model

import com.google.gson.annotations.SerializedName

data class CreditMovieModel(
    @SerializedName("cast")
    val cast : List<Cast>
)
data class Cast(
    @SerializedName("id")
    val id:Int,
    @SerializedName("name")
    val name:String,
    @SerializedName("gender")
    val gender:Int,
    @SerializedName("profile_path")
    val profilePath:String,
    @SerializedName("character")
    val character:String
)

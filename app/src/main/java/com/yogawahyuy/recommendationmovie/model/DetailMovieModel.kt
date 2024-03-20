package com.yogawahyuy.recommendationmovie.model

import com.google.gson.annotations.SerializedName

data class DetailMovieModel(@SerializedName("adult"                 ) var adult               : Boolean?                       ,
                            @SerializedName("backdrop_path"         ) var backdropPath        : String?                        ,
                            @SerializedName("budget"                ) var budget              : Int?                           ,
                            @SerializedName("genres"                ) var genres              : ArrayList<Genres>              ,
                            @SerializedName("homepage"              ) var homepage            : String?                        ,
                            @SerializedName("id"                    ) var id                  : Int?                           ,
                            @SerializedName("imdb_id"               ) var imdbId              : String?                        ,
                            @SerializedName("original_language"     ) var originalLanguage    : String?                        ,
                            @SerializedName("original_title"        ) var originalTitle       : String?                        ,
                            @SerializedName("overview"              ) var overview            : String?                        ,
                            @SerializedName("popularity"            ) var popularity          : Double?                        ,
                            @SerializedName("poster_path"           ) var posterPath          : String?                        ,
                            @SerializedName("production_companies"  ) var productionCompanies : ArrayList<ProductionCompanies> ,
                            @SerializedName("production_countries"  ) var productionCountries : ArrayList<ProductionCountries> ,
                            @SerializedName("release_date"          ) var releaseDate         : String?                        ,
                            @SerializedName("revenue"               ) var revenue             : Int?                           ,
                            @SerializedName("runtime"               ) var runtime             : Int?                           ,
                            @SerializedName("spoken_languages"      ) var spokenLanguages     : ArrayList<SpokenLanguages>     ,
                            @SerializedName("status"                ) var status              : String?                        ,
                            @SerializedName("tagline"               ) var tagline             : String?                        ,
                            @SerializedName("title"                 ) var title               : String?                        ,
                            @SerializedName("video"                 ) var video               : Boolean?                       ,
                            @SerializedName("vote_average"          ) var voteAverage         : Double?                        ,
                            @SerializedName("vote_count"            ) var voteCount           : Int?                           )

data class Genres (
    @SerializedName("id"   ) var id   : Int?    ,
    @SerializedName("name" ) var name : String? 
)

data class ProductionCompanies (
    @SerializedName("id"             ) var id            : Int?    ,
    @SerializedName("logo_path"      ) var logoPath      : String? ,
    @SerializedName("name"           ) var name          : String? ,
    @SerializedName("origin_country" ) var originCountry : String? 
)



data class ProductionCountries (

    @SerializedName("iso_3166_1" ) var iso31661 : String? ,
    @SerializedName("name"       ) var name     : String? 

)
data class SpokenLanguages (

    @SerializedName("english_name" ) var englishName : String? ,
    @SerializedName("iso_639_1"    ) var iso6391     : String? ,
    @SerializedName("name"         ) var name        : String? 

)

package com.example.trilogoapplication.models

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id") var id: Long? = null,
    @SerializedName("original_title") var originalTitle: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("original_language") var movieLanguage: String? = null,
    @SerializedName("overview") var overview: String? = null,
    @SerializedName("popularity") var popularity: Double? = null,
    @SerializedName("poster_path") var posterPath: String? = null,
    @SerializedName("vote_average") var review: String? = null
)
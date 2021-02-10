package com.example.trilogoapplication.models

import com.google.gson.annotations.SerializedName

data class RequestResult(
    @SerializedName("results") val results: List<Movie>
)

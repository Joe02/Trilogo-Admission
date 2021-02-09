package com.example.trilogoapplication

import androidx.lifecycle.ViewModel
import com.example.trilogoapplication.models.Movie
import com.example.trilogoapplication.utils.RetrofitUtil

class HomePageViewModel : ViewModel() {

    fun getMovies(): List<Movie> {

        val movies: List<Movie>

        val retrofitClient =
            RetrofitUtil.getRetrofitInstance("")

        val endpoint = retrofitClient.create(RetrofitUtil.Endpoint::class.java)

        val callback =
            endpoint.getNowPlayingMovies(
                api_key = "ApAzMX7QKcLNsju32Iy8Scf9g0IqJXIr",
                language = "EN-US",
                page = 1
            )

        movies = callback.execute().body()!!

        return movies
    }
}
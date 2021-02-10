package com.example.trilogoapplication.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.trilogoapplication.models.Movie
import com.example.trilogoapplication.models.RequestResult
import com.example.trilogoapplication.utils.RetrofitUtil
import java.lang.Exception

class HomePageViewModel : ViewModel() {

    fun getMovies(): List<Movie> {

        val moviesRequest: RequestResult

        val retrofitClient =
            RetrofitUtil.getRetrofitInstance("https://api.themoviedb.org/3/movie/")

        val endpoint = retrofitClient.create(RetrofitUtil.Endpoint::class.java)

        val callback =
            endpoint.getNowPlayingMovies(
                api_key = "c2e78b4a8c14e65dd6e27504e6df95ad",
                language = "en-US",
                page = 1
            )

        try {
            callback.
            execute().
            body()?.
            let { it ->
                moviesRequest = it

                return moviesRequest.results
            }
        } catch (error: Exception) {
            error.message?.let { Log.e("Error", it) }
        }

        return emptyList()
    }
}
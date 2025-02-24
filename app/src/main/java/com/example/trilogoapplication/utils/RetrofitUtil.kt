package com.example.trilogoapplication.utils

import com.example.trilogoapplication.models.RequestResult
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class RetrofitUtil {

    companion object {

        /** Retorna uma Instância do Client Retrofit para Requisições
         * @param path Caminho Principal da API
         */

        fun getRetrofitInstance(path: String): Retrofit {
            return Retrofit.Builder()
                .baseUrl(path)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }

    interface Endpoint {

        @GET("now_playing")
        fun getNowPlayingMovies(
            @Query("api_key") api_key: String,
            @Query("language") language: String,
            @Query("page") page: Long
        ): Call<RequestResult>

    }
}
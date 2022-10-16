package com.visualprogrammingclass.moviewithapi.retrofit

import com.visualprogrammingclass.moviewithapi.model.MovieDetails
import com.visualprogrammingclass.moviewithapi.model.NowPlaying
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EndPointApi {

    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("api_key") apiKey:String,
        @Query("language") language:String,
        @Query("page") page:Int
    ): Response<NowPlaying>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") Id:Int,
        @Query("api_key") apiKey:String
    ):Response<MovieDetails>

//    @GET("movie/{movie_id}")
//    suspend fun getAMovieFromNowPlaying(
//        @Path("movie_id") Id:Int,
//        @Query("api_key") apiKey:String
//    ):Response<MovieDetails>

}
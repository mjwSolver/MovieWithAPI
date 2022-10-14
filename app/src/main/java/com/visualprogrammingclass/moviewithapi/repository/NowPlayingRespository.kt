package com.visualprogrammingclass.moviewithapi.repository

import com.visualprogrammingclass.moviewithapi.retrofit.EndPointApi
import javax.inject.Inject

class NowPlayingRespository @Inject constructor(private val api: EndPointApi) {

    suspend fun getNowPlayingData(apiKey:String, language:String, page:Int)
        = api.getNowPlaying(apiKey, language, page)

    suspend fun getMovieDetailResults(apiKey:String, id: Int) = api.getMovieDetails(id, apiKey)

//    suspend fun getMovieGenre(apiKey: String, id: Int) = api.getMovieGenre(id, apiKey)

}
package com.visualprogrammingclass.moviewithapi.viewmodel

import android.graphics.Movie
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.visualprogrammingclass.moviewithapi.model.Genre
import com.visualprogrammingclass.moviewithapi.model.MovieDetails
import com.visualprogrammingclass.moviewithapi.model.NowPlayingResult
import com.visualprogrammingclass.moviewithapi.repository.NowPlayingRespository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NowPlayingViewModel @Inject constructor(private val repository: NowPlayingRespository) : ViewModel() {

    private val _nowPlaying: MutableLiveData<ArrayList<NowPlayingResult>> by lazy { MutableLiveData<ArrayList<NowPlayingResult>>() }
    val nowPlaying: LiveData<ArrayList<NowPlayingResult>>
    get() = _nowPlaying


    fun getNowPlaying(apiKey: String, language: String, page: Int) = viewModelScope.launch {
        repository.getNowPlayingData(apiKey, language, page).let { response ->
            if (response.isSuccessful) {
                _nowPlaying.postValue(response.body()?.results as ArrayList<NowPlayingResult>)
            } else {
                Log.e("Get Data", "Failed to get Now Playing Data")
            }
        }
    }

    private val _moviedetail :MutableLiveData<MovieDetails> by lazy { MutableLiveData<MovieDetails>() }
    val moviedetail: LiveData<MovieDetails> get() = _moviedetail

    private val _movieGenre :MutableLiveData<List<Genre>> by lazy { MutableLiveData<List<Genre>>() }
    val movieGenre: LiveData<List<Genre>> get() = _movieGenre

    fun getMovieDetail(apiKey:String, movieid:Int) = viewModelScope.launch {
        repository.getMovieDetailResults(apiKey, movieid).let { response ->
            if(response.isSuccessful){
                _moviedetail.postValue(response.body() as MovieDetails)
                _movieGenre.postValue(response.body()?.genres as List<Genre>)
            } else {
                Log.e("Get Movie Details Data", "failed!")
            }
        }
    }


//    fun getMovieGenre(apiKey:String, movieid: Int) = viewModelScope.launch {
//        repository.getMovieGenre(apiKey, movieid).let { response ->
//            if(response.isSuccessful){
//                _movieGenre.postValue(response.body()?.genres as List<Genre>)
//            } else {
//                Log.e("Get Movie Genre Data", "failed!")
//            }
//        }
//    }






}

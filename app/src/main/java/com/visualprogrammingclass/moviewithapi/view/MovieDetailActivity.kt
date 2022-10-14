package com.visualprogrammingclass.moviewithapi.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.visualprogrammingclass.moviewithapi.adapter.GenreAdapter
import com.visualprogrammingclass.moviewithapi.adapter.NowPlayingAdapter
import com.visualprogrammingclass.moviewithapi.databinding.ActivityMovieDetailBinding
import com.visualprogrammingclass.moviewithapi.helper.Const
import com.visualprogrammingclass.moviewithapi.viewmodel.MovieDetailViewModel
import com.visualprogrammingclass.moviewithapi.viewmodel.NowPlayingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {

    private lateinit var bind:ActivityMovieDetailBinding
    private lateinit var model:NowPlayingViewModel
    private lateinit var adaptery: GenreAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(bind.root)

        val movieID = intent.getIntExtra("movieid", 0)
        Toast.makeText(applicationContext, "Movie ID: $movieID", Toast.LENGTH_SHORT).show()

        model = ViewModelProvider(this)[NowPlayingViewModel::class.java]

        model.getMovieDetail(Const.apikey, movieID)
        model.moviedetail.observe(this) { response ->

            bind.movieTitleInDetailTextView.apply { text = response.title }

            Glide.with(applicationContext)
                .load(Const.IMG_URL + response.backdrop_path)
                .into(bind.imageView)

        }

        model.movieGenre.observe(this){ response ->
            bind.genreRecyclerView.layoutManager = LinearLayoutManager(this)
            adaptery = GenreAdapter(response)
            bind.genreRecyclerView.adapter = adaptery
        }

        // to be continued



    }



}
package com.visualprogrammingclass.moviewithapi.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.visualprogrammingclass.moviewithapi.adapter.CompanyAdapter
import com.visualprogrammingclass.moviewithapi.adapter.GenreAdapter
import com.visualprogrammingclass.moviewithapi.databinding.ActivityMovieDetailBinding
import com.visualprogrammingclass.moviewithapi.helper.Const
import com.visualprogrammingclass.moviewithapi.viewmodel.NowPlayingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {

    private lateinit var bind:ActivityMovieDetailBinding
    private lateinit var model:NowPlayingViewModel
    private lateinit var genreAdaptery: GenreAdapter
    private lateinit var companyAdapter: CompanyAdapter
    private lateinit var horizontalLinearLayoutManager:LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(bind.root)

        val movieID = intent.getIntExtra("movieid", 0)
//        Toast.makeText(applicationContext, "Movie ID: $movieID", Toast.LENGTH_SHORT).show()

        // initialize the ViewModel
        model = ViewModelProvider(this)[NowPlayingViewModel::class.java]
        // start getting the New Information
        model.getMovieDetail(Const.apikey, movieID)

        // Set up the LinearLayoutManager
        horizontalLinearLayoutManager = LinearLayoutManager(this)
        horizontalLinearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL

        // observers subscribing to changes in viewModel
        observers()

    }

    fun observers() {

        model.moviedetail.observe(this) { response ->
            bind.movieTitleInDetailTextView.apply { text = response.title }

            Glide.with(applicationContext)
                .load(Const.IMG_URL + response.backdrop_path)
                .into(bind.imageView)

        }

        model.movieGenre.observe(this){ response ->
            bind.genreRecyclerView.layoutManager = horizontalLinearLayoutManager
            genreAdaptery = GenreAdapter(response)
            bind.genreRecyclerView.adapter = genreAdaptery
        }

        model.productionCompany.observe(this){response ->
            bind.productionCompanyRecyclerView.layoutManager = horizontalLinearLayoutManager
            companyAdapter = CompanyAdapter(response)
            bind.productionCompanyRecyclerView.adapter = companyAdapter
        }

        model.movieDescription.observe(this){response ->
            bind.movieDescriptionTextView.text = response
        }

    }

}
package com.visualprogrammingclass.moviewithapi.view

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
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
    private lateinit var horizontalGenreLinearLayoutManager:LinearLayoutManager
    private lateinit var horizontalProductionCompanyLinearLayoutManager:LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(bind.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
//        setSupportActionBar(bind.movieDetailsToolBar)
        supportActionBar?.title = "back"

        val movieID = intent.getIntExtra("movieid", 0)
//        Toast.makeText(applicationContext, "Movie ID: $movieID", Toast.LENGTH_SHORT).show()

        runLoadingAnimation()

        // initialize the ViewModel
        model = ViewModelProvider(this)[NowPlayingViewModel::class.java]
        // start getting the New Information
        model.getMovieDetail(Const.apikey, movieID)

        // Set up the LinearLayoutManager
        horizontalGenreLinearLayoutManager = LinearLayoutManager(this)
        horizontalGenreLinearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL

        horizontalProductionCompanyLinearLayoutManager = LinearLayoutManager(this)
        horizontalProductionCompanyLinearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL

        // observers subscribing to changes in viewModel
        observers()

    }

    fun observers() {

        var loaded: RequestBuilder<Drawable>

        model.moviedetail.observe(this) { response ->
            bind.movieTitleInDetailTextView.apply { text = response.title }

            loaded = Glide.with(applicationContext).load(Const.IMG_URL + response.backdrop_path)
            loaded.into(bind.moviePosterBackFropImageView)

        }

        model.posterPath.observe(this){response->
            loaded = Glide.with(applicationContext).load(Const.IMG_URL + response)
            loaded.into(bind.moviePosterImageView)
        }

        model.movieGenre.observe(this){ response ->
            bind.genreRecyclerView.layoutManager = horizontalGenreLinearLayoutManager
            genreAdaptery = GenreAdapter(response)
            bind.genreRecyclerView.adapter = genreAdaptery
        }

        model.productionCompany.observe(this){response ->
            bind.productionCompanyRecyclerView.layoutManager = horizontalProductionCompanyLinearLayoutManager
            companyAdapter = CompanyAdapter(response)
            bind.productionCompanyRecyclerView.adapter = companyAdapter
        }

        model.movieDescription.observe(this){response ->
            bind.movieDescriptionTextView.text = response
        }

        model.originalLanguage.observe(this){response ->
            bind.languageTextView.text = response
        }
    }

    private fun runLoadingAnimation(){
        val loading = LoadingDialog(this)
        loading.startLoading(this)
        Handler(Looper.getMainLooper()).postDelayed({ loading.isDismiss() }, 1000)

    }

}
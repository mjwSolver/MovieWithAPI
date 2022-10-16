package com.visualprogrammingclass.moviewithapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.visualprogrammingclass.moviewithapi.adapter.NowPlayingAdapter
import com.visualprogrammingclass.moviewithapi.databinding.ActivityMainBinding
import com.visualprogrammingclass.moviewithapi.helper.Const
import com.visualprogrammingclass.moviewithapi.model.NowPlaying
import com.visualprogrammingclass.moviewithapi.viewmodel.NowPlayingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var bind : ActivityMainBinding
    private lateinit var adaptery: NowPlayingAdapter
    private lateinit var viewModel: NowPlayingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        supportActionBar?.title = "Now Playing"

        viewModel = ViewModelProvider(this)[NowPlayingViewModel::class.java]
        viewModel.getNowPlaying(Const.apikey, "en-US", 1)

        viewModel.nowPlaying.observe(this) { response ->
            bind.rvMainActivity.layoutManager = LinearLayoutManager(this)
            adaptery = NowPlayingAdapter(response)
            bind.rvMainActivity.adapter = adaptery
        }




    }



}
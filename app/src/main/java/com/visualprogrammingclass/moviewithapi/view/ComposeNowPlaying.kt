package com.visualprogrammingclass.moviewithapi.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.visualprogrammingclass.moviewithapi.helper.Const
import com.visualprogrammingclass.moviewithapi.model.NowPlaying
import com.visualprogrammingclass.moviewithapi.model.NowPlayingResult
import com.visualprogrammingclass.moviewithapi.view.ui.theme.MovieWithAPITheme
import com.visualprogrammingclass.moviewithapi.view.widgets.MovieCard
import com.visualprogrammingclass.moviewithapi.viewmodel.MovieDetailViewModel
import com.visualprogrammingclass.moviewithapi.viewmodel.NowPlayingViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.w3c.dom.Text

@AndroidEntryPoint
class ComposeNowPlaying : ComponentActivity() {

    private lateinit var MainViewModel:NowPlayingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MainViewModel = ViewModelProvider(this)[NowPlayingViewModel::class.java]
        MainViewModel.getNowPlaying(Const.apikey, "en-US", 1)
        MainViewModel.nowPlaying.observe(this){ response ->
            // Menampilkan data di layar
            setContent {
                MovieWithAPITheme {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        MovieList(movieList = response)
                    }
                }
            }
        }

    }
}

@Composable
fun MovieList(movieList:List<NowPlayingResult>) {
    LazyColumn{
        itemsIndexed(items = movieList){ index,item ->
            MovieCard(movie = item)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MovieWithAPITheme {
//        MovieList("Android")
    }
}
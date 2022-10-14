package com.visualprogrammingclass.moviewithapi.model

data class NowPlaying(
    val dates: Dates,
    val page: Int,
    val results: List<NowPlayingResult>,
    val total_pages: Int,
    val total_results: Int
)
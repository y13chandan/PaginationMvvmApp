package com.example.paginationmvvmapp.model

data class Session(
    val name: String,
    val listener_count: Int,
    val genres: List<String>,
    val current_track: CurrentTrack
) {
    fun getGenreString(): String {
        var genreText = ""
        for(genre in genres){
            genreText += "$genre "
        }
        return  genreText
    }
}

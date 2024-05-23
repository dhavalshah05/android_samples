package com.sample.room.database.manyToMany

data class PlaylistWithSongCount(
    val playlistId: Long,
    val playlistName: String,
    val songCount: Int
)
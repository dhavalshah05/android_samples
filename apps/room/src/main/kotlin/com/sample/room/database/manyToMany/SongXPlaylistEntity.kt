package com.sample.room.database.manyToMany

import androidx.room.Entity

@Entity(
    primaryKeys = ["songId", "playlistId"]
)
data class SongXPlaylistEntity(
    val songId: Long,
    val playlistId: Long,
)
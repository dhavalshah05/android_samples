package com.sample.room.database.manyToMany

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class SongWithPlaylists(
    @Embedded val song: SongEntity,
    @Relation(
        entityColumn = "id",
        parentColumn = "id",
        associateBy = Junction(SongXPlaylistEntity::class, parentColumn = "songId", entityColumn = "playlistId")
    )
    val playlists: List<PlaylistEntity>
)

package com.sample.room.database.manyToMany

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert

@Dao
interface ManyToManyRelationDao {
    @Upsert
    fun upsertSong(item: SongEntity): Long

    @Upsert
    fun upsertPlaylist(item: PlaylistEntity): Long

    @Upsert
    fun upsertSongXPlaylist(item: SongXPlaylistEntity)

    @Transaction
    @Query("SELECT * FROM SongEntity")
    fun getSongsWithPlaylists(): List<SongWithPlaylists>

    @Transaction
    @Query("SELECT * FROM PlaylistEntity")
    fun getPlaylistsWithSongs(): List<PlaylistWithSongs>

    @Transaction
    @Query("""
        select PlaylistEntity.id as playlistId,
        PlaylistEntity.name as playlistName,
        count(SongXPlaylistEntity.songId) as songCount
        from PlaylistEntity
        left join SongXPlaylistEntity on PlaylistEntity.id = SongXPlaylistEntity.playlistId
        group by PlaylistEntity.id, PlaylistEntity.name
    """)
    fun getPlaylistsWithSongCount(): List<PlaylistWithSongCount>
}


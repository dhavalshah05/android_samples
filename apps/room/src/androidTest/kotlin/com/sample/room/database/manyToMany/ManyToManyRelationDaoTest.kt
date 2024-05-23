package com.sample.room.database.manyToMany

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.sample.room.database.AppDatabase
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.IOException

class ManyToManyRelationDaoTest {

    private lateinit var db: AppDatabase
    private lateinit var manyToManyRelationDao: ManyToManyRelationDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .build()
        manyToManyRelationDao = db.manyToManyRelationDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun getSongsWithPlaylists() {
        // Arrange
        val songId = manyToManyRelationDao.upsertSong(SongEntity(name = "Something like that"))

        val playlist1Id = manyToManyRelationDao.upsertPlaylist(PlaylistEntity(name = "Journey"))
        manyToManyRelationDao.upsertSongXPlaylist(SongXPlaylistEntity(songId = songId, playlistId = playlist1Id))

        val playlist2Id = manyToManyRelationDao.upsertPlaylist(PlaylistEntity(name = "Rock"))
        manyToManyRelationDao.upsertSongXPlaylist(SongXPlaylistEntity(songId = songId, playlistId = playlist2Id))

        // Act
        val songsWithPlaylists = manyToManyRelationDao.getSongsWithPlaylists()

        // Assert
        Assert.assertEquals(1, songsWithPlaylists.size)

        val firstItem = songsWithPlaylists.first()
        Assert.assertEquals(songId, firstItem.song.id)
        Assert.assertEquals(2, firstItem.playlists.size)
    }

    @Test
    fun getPlaylistsWithSongs() {
        // Arrange
        val song1Id = manyToManyRelationDao.upsertSong(SongEntity(name = "Something like that"))
        val song2Id = manyToManyRelationDao.upsertSong(SongEntity(name = "Let her go"))
        val playlistId = manyToManyRelationDao.upsertPlaylist(PlaylistEntity(name = "Rock"))

        manyToManyRelationDao.upsertSongXPlaylist(SongXPlaylistEntity(songId = song1Id, playlistId = playlistId))
        manyToManyRelationDao.upsertSongXPlaylist(SongXPlaylistEntity(songId = song2Id, playlistId = playlistId))

        // Act
        val result = manyToManyRelationDao.getPlaylistsWithSongs()

        // Assert
        Assert.assertEquals(1, result.size)

        val firstItem = result.first()
        Assert.assertEquals(playlistId, firstItem.playlist.id)
        Assert.assertEquals(2, firstItem.songs.size)
    }
}
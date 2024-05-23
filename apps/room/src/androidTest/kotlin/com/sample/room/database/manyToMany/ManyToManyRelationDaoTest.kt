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

    @Test
    fun getPlaylistsWithSongCount() {
        // Arrange
        val playlistRockingRollId = manyToManyRelationDao.upsertPlaylist(PlaylistEntity(name = "Rocking Roll"))
        val playlistPopMusicId = manyToManyRelationDao.upsertPlaylist(PlaylistEntity(name = "Pop Music"))
        val playlistSufiMusicId = manyToManyRelationDao.upsertPlaylist(PlaylistEntity(name = "Sufi Music"))

        val song1Id = manyToManyRelationDao.upsertSong(SongEntity(name = "Let her go"))
        val song2Id = manyToManyRelationDao.upsertSong(SongEntity(name = "Everything has changed"))
        val song3Id = manyToManyRelationDao.upsertSong(SongEntity(name = "All men must die"))
        val song4Id = manyToManyRelationDao.upsertSong(SongEntity(name = "Song of ice and fire"))

        manyToManyRelationDao.upsertSongXPlaylist(SongXPlaylistEntity(songId = song1Id, playlistId = playlistRockingRollId))
        manyToManyRelationDao.upsertSongXPlaylist(SongXPlaylistEntity(songId = song2Id, playlistId = playlistRockingRollId))
        manyToManyRelationDao.upsertSongXPlaylist(SongXPlaylistEntity(songId = song3Id, playlistId = playlistRockingRollId))
        manyToManyRelationDao.upsertSongXPlaylist(SongXPlaylistEntity(songId = song4Id, playlistId = playlistPopMusicId))

        // Act
        val result = manyToManyRelationDao.getPlaylistsWithSongCount()

        // Assert
        Assert.assertEquals(3, result.size)
        Assert.assertEquals(3, result.find { it.playlistId == playlistRockingRollId }?.songCount)
        Assert.assertEquals(1, result.find { it.playlistId == playlistPopMusicId }?.songCount)
        Assert.assertEquals(0, result.find { it.playlistId == playlistSufiMusicId }?.songCount)
    }
}
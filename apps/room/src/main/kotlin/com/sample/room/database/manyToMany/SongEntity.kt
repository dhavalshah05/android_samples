package com.sample.room.database.manyToMany

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SongEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
)
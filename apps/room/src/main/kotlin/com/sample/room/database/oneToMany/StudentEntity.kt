package com.sample.room.database.oneToMany

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class
StudentEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val schoolId: Long,
)

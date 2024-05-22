package com.sample.room.database.oneToOne

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LoginCredentialEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val email: String,
    val password: String,
    val userId: Long,
)

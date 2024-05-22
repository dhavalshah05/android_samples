package com.sample.room.database.oneToOne

import androidx.room.Embedded
import androidx.room.Relation

data class UserLoginCredential(
    @Embedded val user: UserEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "userId"
    )
    val loginCredential: LoginCredentialEntity,
)

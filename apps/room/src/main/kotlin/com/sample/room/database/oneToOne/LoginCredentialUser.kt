package com.sample.room.database.oneToOne

import androidx.room.Embedded
import androidx.room.Relation

data class LoginCredentialUser(
    @Embedded val loginCredentialEntity: LoginCredentialEntity,
    @Relation(
        entityColumn = "id",
        parentColumn = "userId"
    )
    val userEntity: UserEntity,
)

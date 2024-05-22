package com.sample.room.database.oneToOne

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert

@Dao
interface OneToOneRelationDao {
    @Upsert
    fun upsertUser(item: UserEntity): Long

    @Upsert
    fun upsertLoginCredential(item: LoginCredentialEntity): Long

    @Transaction
    @Query("SELECT * FROM UserEntity")
    fun getUserAndLoginCredential(): List<UserLoginCredential>

    @Transaction
    @Query("SELECT * FROM LoginCredentialEntity")
    fun getLoginCredentialAndUser(): List<LoginCredentialUser>
}
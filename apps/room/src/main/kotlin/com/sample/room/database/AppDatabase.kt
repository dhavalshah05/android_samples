package com.sample.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sample.room.database.expense.ExpenseDao
import com.sample.room.database.expense.ExpenseEntity
import com.sample.room.database.manyToMany.ManyToManyRelationDao
import com.sample.room.database.manyToMany.PlaylistEntity
import com.sample.room.database.manyToMany.SongEntity
import com.sample.room.database.manyToMany.SongXPlaylistEntity
import com.sample.room.database.oneToMany.OneToManyRelationDao
import com.sample.room.database.oneToMany.SchoolEntity
import com.sample.room.database.oneToMany.StudentEntity
import com.sample.room.database.oneToOne.LoginCredentialEntity
import com.sample.room.database.oneToOne.OneToOneRelationDao
import com.sample.room.database.oneToOne.UserEntity

@Database(
    version = 1,
    entities = [
        ExpenseEntity::class,
        UserEntity::class,
        LoginCredentialEntity::class,
        SchoolEntity::class,
        StudentEntity::class,
        SongEntity::class,
        PlaylistEntity::class,
        SongXPlaylistEntity::class,
    ]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun expenseDao(): ExpenseDao
    abstract fun oneToOneRelationDao(): OneToOneRelationDao
    abstract fun oneToManyRelationDao(): OneToManyRelationDao
    abstract fun manyToManyRelationDao(): ManyToManyRelationDao
}
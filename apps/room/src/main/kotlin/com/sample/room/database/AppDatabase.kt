package com.sample.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sample.room.database.expense.ExpenseDao
import com.sample.room.database.expense.ExpenseEntity
import com.sample.room.database.oneToOne.LoginCredentialEntity
import com.sample.room.database.oneToOne.OneToOneRelationDao
import com.sample.room.database.oneToOne.UserEntity

@Database(
    version = 1,
    entities = [
        ExpenseEntity::class,
        UserEntity::class,
        LoginCredentialEntity::class,
    ]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun expenseDao(): ExpenseDao
    abstract fun oneToOneRelationDao(): OneToOneRelationDao
}
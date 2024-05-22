package com.sample.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sample.room.database.dao.ExpenseDao
import com.sample.room.database.entity.ExpenseEntity

@Database(
    version = 1,
    entities = [
        ExpenseEntity::class
    ]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao
}
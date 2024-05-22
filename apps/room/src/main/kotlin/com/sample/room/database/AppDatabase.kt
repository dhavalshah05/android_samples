package com.sample.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sample.room.database.expense.ExpenseDao
import com.sample.room.database.expense.ExpenseEntity

@Database(
    version = 1,
    entities = [
        ExpenseEntity::class
    ]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao
}
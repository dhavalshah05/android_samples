package com.sample.room.database.expense

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface ExpenseDao {
    @Upsert
    fun upsert(item: ExpenseEntity): Long

    @Upsert
    fun upsertAll(vararg items: ExpenseEntity): List<Long>

    @Query("DELETE FROM ExpenseEntity WHERE id = :id")
    fun deleteExpenseById(id: Long)

    @Query("SELECT * FROM ExpenseEntity")
    fun getAll(): List<ExpenseEntity>
}
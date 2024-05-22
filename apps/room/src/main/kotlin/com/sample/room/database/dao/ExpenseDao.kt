package com.sample.room.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.sample.room.database.entity.ExpenseEntity

@Dao
interface ExpenseDao {
    @Upsert
    fun upsertAll(vararg items: ExpenseEntity)

    @Delete
    fun delete(item: ExpenseEntity)

    @Query("DELETE FROM ExpenseEntity WHERE id = :id")
    fun delete(id: Long)

    @Query("DELETE FROM ExpenseEntity")
    fun deleteAll()

    @Query("SELECT * FROM ExpenseEntity")
    fun getAll(): List<ExpenseEntity>
}
package com.sample.room.database.expense

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ExpenseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val amount: Int,
    val description: String,
    val type: ExpenseType = ExpenseType.NON_RECURRING,
)
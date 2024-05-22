package com.sample.room.database.expense

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.sample.room.database.AppDatabase
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.IOException

class ExpenseEntityTest {

    private lateinit var db: AppDatabase
    private lateinit var expenseDao: ExpenseDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .build()
        expenseDao = db.expenseDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun createExpense() {
        // Arrange
        val expense = ExpenseEntity(
            id = 0,
            amount = 2000,
            description = "Fuel for i10 Nios"
        )

        // Act
        expenseDao.upsert(expense)

        // Assert
        val result = expenseDao.getAll()
        Assert.assertEquals(1, result.size)
        Assert.assertTrue(result.first().id > 0)
        println(result.first())
    }

    @Test
    fun createExpenses() {
        // Arrange
        val expense1 = ExpenseEntity(
            id = 0,
            amount = 2000,
            description = "Fuel for i10 Nios"
        )
        val expense2 = ExpenseEntity(
            id = 0,
            amount = 1499,
            description = "Jio Recharge"
        )

        // Act
        val result = expenseDao.upsertAll(expense1, expense2)

        // Assert
        Assert.assertEquals(2, result.size)
        Assert.assertTrue(result.all { it > 0 })
    }

    @Test
    fun deleteExpenseById() {
        // Arrange
        val expense1 = ExpenseEntity(
            id = 0,
            amount = 2000,
            description = "Fuel for i10 Nios"
        )
        val expense2 = ExpenseEntity(
            id = 0,
            amount = 1499,
            description = "Jio Recharge"
        )
        val expenseIds = expenseDao.upsertAll(expense1, expense2)

        // Act
        expenseDao.deleteExpenseById(expenseIds.first())
        val actualExpenses = expenseDao.getAll()

        // Assert
        Assert.assertEquals(1, actualExpenses.size)
    }
}
package com.sample.room

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.sample.room.database.AppDatabase
import com.sample.room.database.dao.ExpenseDao
import com.sample.room.database.entity.ExpenseEntity
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
    fun createExpenseAndGet() {
        // Arrange
        val expense = ExpenseEntity(
            id = 0,
            amount = 2000,
            description = "Fuel for i10 Nios"
        )

        // Act
        expenseDao.upsertAll(expense)

        // Assert
        val result = expenseDao.getAll()
        Assert.assertEquals(1, result.size)
    }
}
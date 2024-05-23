package com.sample.room.database.oneToOne

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.sample.room.database.AppDatabase
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.IOException

class OneToOneRelationDaoTest {

    private lateinit var db: AppDatabase
    private lateinit var oneToOneRelationDao: OneToOneRelationDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .build()
        oneToOneRelationDao = db.oneToOneRelationDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun getUserWithLoginCredential() {
        // Arrange
        val userId = oneToOneRelationDao.upsertUser(UserEntity(name = "Jon Snow"))
        val loginCredentialId = oneToOneRelationDao.upsertLoginCredential(LoginCredentialEntity(
            email = "jon@gmail.com",
            password = "Abc@123#",
            userId = userId
        ))

        // Act
        val result = oneToOneRelationDao.getUserAndLoginCredential()

        // Assert
        Assert.assertTrue(userId > 0)
        Assert.assertTrue(loginCredentialId > 0)
        Assert.assertEquals(1, result.size)

        val userWithLoginCredential = result.first()
        println(userWithLoginCredential)

        Assert.assertEquals(userId, userWithLoginCredential.user.id)
        Assert.assertEquals(loginCredentialId, userWithLoginCredential.loginCredential.id)
    }

    @Test
    fun getLoginCredentialWithUser() {
        // Arrange
        val userId = oneToOneRelationDao.upsertUser(UserEntity(name = "Jon Snow"))
        val loginCredentialId = oneToOneRelationDao.upsertLoginCredential(LoginCredentialEntity(
            email = "jon@gmail.com",
            password = "Abc@123#",
            userId = userId
        ))

        // Act
        val result = oneToOneRelationDao.getLoginCredentialAndUser()

        // Assert
        Assert.assertTrue(userId > 0)
        Assert.assertTrue(loginCredentialId > 0)
        Assert.assertEquals(1, result.size)

        val loginCredentialUser = result.first()
        println(loginCredentialUser)

        Assert.assertEquals(userId, loginCredentialUser.userEntity.id)
        Assert.assertEquals(loginCredentialId, loginCredentialUser.loginCredentialEntity.id)
    }
}
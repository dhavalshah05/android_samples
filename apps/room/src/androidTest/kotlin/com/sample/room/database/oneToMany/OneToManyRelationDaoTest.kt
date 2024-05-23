package com.sample.room.database.oneToMany

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.sample.room.database.AppDatabase
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.IOException

class OneToManyRelationDaoTest {

    private lateinit var db: AppDatabase
    private lateinit var oneToManyRelationDao: OneToManyRelationDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .build()
        oneToManyRelationDao = db.oneToManyRelationDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun getSchoolWithStudents() {
        // Arrange
        val schoolId = oneToManyRelationDao.upsertSchool(SchoolEntity(name = "Lotus"))

        oneToManyRelationDao.upsertStudent(StudentEntity(name = "Jon", schoolId = schoolId))
        oneToManyRelationDao.upsertStudent(StudentEntity(name = "Bran", schoolId = schoolId))

        // Act
        val result = oneToManyRelationDao.getSchoolWithStudents()

        // Assert
        Assert.assertEquals(1, result.size)
        val schoolWithStudents = result.first()
        println(schoolWithStudents)

        Assert.assertEquals(2, schoolWithStudents.students.size)
    }

    @Test
    fun getStudentWithSchool() {
        // Arrange
        val schoolId = oneToManyRelationDao.upsertSchool(SchoolEntity(name = "Lotus"))

        oneToManyRelationDao.upsertStudent(StudentEntity(name = "Jon", schoolId = schoolId))
        oneToManyRelationDao.upsertStudent(StudentEntity(name = "Bran", schoolId = schoolId))

        // Act
        val result = oneToManyRelationDao.getStudentWithSchool()

        // Assert
        Assert.assertEquals(2, result.size)
        Assert.assertTrue(result.all { it.school.id == schoolId })
    }
}
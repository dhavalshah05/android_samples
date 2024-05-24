package com.sample.room.database.oneToMany

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert

@Dao
interface OneToManyRelationDao {
    @Upsert
    fun upsertSchool(item: SchoolEntity): Long

    @Upsert
    fun upsertStudent(item: StudentEntity): Long

    @Transaction
    @Query("SELECT * FROM SchoolEntity")
    fun getSchoolWithStudents(): List<SchoolWithStudents>

    @Transaction
    @Query("SELECT * FROM StudentEntity")
    fun getStudentWithSchool(): List<StudentWithSchool>

    @Query("""
        select StudentEntity.*,
        SchoolEntity.name as schoolName
        from StudentEntity
        inner join SchoolEntity on StudentEntity.schoolId = SchoolEntity.id
    """)
    fun getStudentsWithSchoolName(): List<StudentWithSchoolName>
}
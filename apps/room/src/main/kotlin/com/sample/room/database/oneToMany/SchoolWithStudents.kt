package com.sample.room.database.oneToMany

import androidx.room.Embedded
import androidx.room.Relation

data class SchoolWithStudents(
    @Embedded val school: SchoolEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "schoolId",
    )
    val students: List<StudentEntity>,
)

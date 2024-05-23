package com.sample.room.database.oneToMany

import androidx.room.Embedded
import androidx.room.Relation

data class StudentWithSchool(
    @Embedded val student: StudentEntity,
    @Relation(
        entityColumn = "id",
        parentColumn = "schoolId"
    )
    val school: SchoolEntity,
)

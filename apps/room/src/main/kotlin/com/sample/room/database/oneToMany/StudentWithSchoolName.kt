package com.sample.room.database.oneToMany

import androidx.room.Embedded

data class StudentWithSchoolName(
    @Embedded val student: StudentEntity,
    val schoolName: String,
)

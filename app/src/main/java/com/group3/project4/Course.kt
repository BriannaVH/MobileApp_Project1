package com.group3.project4

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "courses")
class Course {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "courseId")
    var id: Int = 0

    @ColumnInfo(name = "courseName")
    var courseName: String = ""

    @ColumnInfo(name = "creditHour")
    var creditHour: Int = 0

    @ColumnInfo(name = "letterGrade")
    var letterGrade: String = ""

    constructor()

    constructor(coursename: String, creditHour: Int, letterGrade: String) {
        this.courseName = coursename
        this.creditHour = creditHour
        this.letterGrade = letterGrade
    }
}
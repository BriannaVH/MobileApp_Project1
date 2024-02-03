package com.example.bottombardemo.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlin.random.Random


@Composable
fun Academics() {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Icon(
            imageVector = Icons.Filled.Favorite,
            contentDescription = "favorites",
            tint = Color.Blue // ,
//            modifier = Modifier.size(150.dp)
//                .align(Alignment.Center)
        )

        Courses()
        GPACalculator()
    }
}

@Composable
fun GPACalculator() {
    // State to hold the GPA value
    var gpa by remember { mutableStateOf(0.0f) }

    // Function to generate a random GPA
    fun generateRandomGpa(): Float {
        return Random.nextFloat() * 4.0f
    }

    Column {
        // Button to calculate GPA
        Button(onClick = { gpa = generateRandomGpa() }) {
            Text("Calculate GPA")
        }

        // Display the generated GPA
        if (gpa >= 0.0) {
            Text("Your GPA is: ${"%.2f".format(gpa)}")
        }
    }
}


@Composable
fun Courses() {
    val courses = listOf(
        Course("CS101", "Intro to Prog", 4, "A"),
        Course("CS102", "Data Struct", 3, "B+"),
        Course("CS103", "Algorithms", 3, "A-"),
        Course("CS104", "Comp Networks", 3, "B"),
        Course("CS105", "Op Systems", 4, "B+")
    )

    Column {
        courses.forEach { course ->
            CourseRow(course)
        }

        Button(onClick = { /* Add course logic here */ }) {
            Text("Add")
        }
    }
}

@Composable
fun CourseRow(course: Course) {
    Row {
        Text(course.id)
        Text(course.name)
        Text(course.creditHours.toString())
        Text(course.letterGrade)
        Button(onClick = { /* Edit course logic here */ }) {
            Text("Edit")
        }
        Button(onClick = { /* Delete course logic here */ }) {
            Text("Delete")
        }
    }
}

data class Course(val id: String, val name: String, val creditHours: Int, val letterGrade: String)

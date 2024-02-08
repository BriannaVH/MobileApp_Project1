package com.example.bottombardemo.screens

import android.graphics.Paint.Align
import android.text.Layout
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bottombardemo.Course
import com.example.bottombardemo.MainViewModel
import java.math.RoundingMode
import kotlin.random.Random


@Composable
fun Academics(
    allCourses: List<Course>,
    searchResults: List<Course>,
    viewModel: MainViewModel
) {
    var courseName by remember { mutableStateOf("") }
    var courseCreditHour by remember { mutableStateOf("") }
    var letterGrade by remember {
        mutableStateOf("")
    }
    val gradePoints = mapOf("A+" to 4.00, "A" to 4.00, "A-" to 3.67, "B+" to 3.33 , "B" to 3.00, "B-" to 2.67, "C+" to 2.33, "C" to 2.00, "C-" to 1.67, "D+" to 1.33, "D" to 1.00, "D-" to .67, "F" to 0)


    var calculatedGPA by remember {
        mutableStateOf(0.0)
    }

    var searching by remember { mutableStateOf(false) }

    val onCourseTextChange = { text : String ->
        courseName = text
    }

    val onCreditHourTextChange = { text : String ->
        courseCreditHour = text
    }

    val onLetterGradeTextChange = { text : String ->
        letterGrade = text
    }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        CustomTextField(
            title = "Course Name",
            textState = courseName,
            onTextChange = onCourseTextChange,
            keyboardType = KeyboardType.Text
        )

        CustomTextField(
            title = "Credit Hour",
            textState = courseCreditHour,
            onTextChange = onCreditHourTextChange,
            keyboardType = KeyboardType.Number
        )

        CustomTextField(
            title = "Letter Grade",
            textState = letterGrade,
            onTextChange = onLetterGradeTextChange,
            keyboardType = KeyboardType.Text
        )


        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Button(onClick = {
                if (courseCreditHour.isNotEmpty()
                    && courseCreditHour.toDoubleOrNull()!==null
                    && gradePoints.containsKey(letterGrade)) {
                    viewModel.insertCourse(
                        Course(
                            courseName,
                            courseCreditHour.toInt(),
                            letterGrade
                        )
                    )
                    searching = false
                }
            }) {
                Text("Add")
            }

            Button(onClick = {
                searching = true
                viewModel.findCourse(courseName)
            }) {
                Text("Sch")
            }

            Button(onClick = {
                searching = false
                viewModel.deleteCourse(courseName)
            }) {
                Text("Del")
            }

            Button(onClick = {
                searching = false
                courseName = ""
                courseCreditHour = ""
                letterGrade = ""
            }// ,
//                modifier = Modifier.size(width = 80.dp, height = 50.dp)

            ) {
                Text("Clr")
            }

            Button(onClick = {

                var sum : Double = 0.0
                var totalCredits = 0
                for (i in allCourses){
                    val points_str = gradePoints[i.letterGrade].toString()
                    val points : Double = points_str.toDouble()
                        sum += i.creditHour * points
                    totalCredits += i.creditHour
                }
                calculatedGPA = sum / totalCredits
                calculatedGPA = calculatedGPA.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN).toDouble()

            }) {
                Text("GPA")
            }
        }

        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ){
            Surface(
            ){
                Spacer(modifier = Modifier.weight(1f))
                Text("GPA: " + calculatedGPA.toString(), textAlign = TextAlign.Left)
            }

        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ){
            LazyColumn(
                Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                val list = if (searching) searchResults else allCourses

                item {
                    TitleRow(head1 = "ID", head2 = "Course", head3 = "Credit Hours", head4 = "Grades")
                }

                items(list) { course ->
                    CourseRow(id = course.id, name = course.courseName,
                        creditHour = course.creditHour,
                        letterGrade = course.letterGrade)
                }
            }
        }


    }

}



@Composable
fun TitleRow(head1: String, head2: String, head3: String, head4: String) {
    Row(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Text(head1, color = Color.White,
            modifier = Modifier
                .weight(0.1f),
            textAlign = TextAlign.Center
        )
        Text(head2, color = Color.White,
            modifier = Modifier
                .weight(0.2f),
            textAlign = TextAlign.Center
        )
        Text(head3, color = Color.White,
            modifier = Modifier.weight(0.2f),
            textAlign = TextAlign.Center
        )
        Text(head4, color = Color.White,
            modifier = Modifier.weight(0.2f),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun CourseRow(id: Int, name: String, creditHour: Int, letterGrade: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Text(id.toString(), modifier = Modifier
            .weight(0.1f),
            textAlign = TextAlign.Center)
        Text(name, modifier = Modifier.weight(0.2f),
            textAlign = TextAlign.Center)
        Text(creditHour.toString(), modifier = Modifier.weight(0.2f),
            textAlign = TextAlign.Center)
        Text(letterGrade, modifier = Modifier.weight(0.2f),
            textAlign = TextAlign.Center)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    title: String,
    textState: String,
    onTextChange: (String) -> Unit,
    keyboardType: KeyboardType
) {
    OutlinedTextField(
        value = textState,
        onValueChange = { onTextChange(it) },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        singleLine = true,
        label = { Text(title)},
        modifier = Modifier.padding(10.dp),
        textStyle = TextStyle(fontWeight = FontWeight.Bold,
            fontSize = 30.sp)
    )
}


package com.example.bottombardemo.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.bottombardemo.MainViewModel
import com.example.bottombardemo.TrivialQuestion

/**
 * Making private variables
 * https://stackoverflow.com/questions/65641635/jetpack-compose-how-can-we-call-a-composable-function-inside-an-onclick
 */
private var updateQuestionsList = mutableStateOf(false)
private var numberOfQuestions = mutableStateOf(10) //default of 10 questions

@Composable
fun Trivial(viewModel: MainViewModel) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Icon(
            imageVector = Icons.Filled.Favorite,
            contentDescription = "favorites",
            tint = Color.Blue
        )

        numberInputField()



        Button(
            onClick = {
                updateQuestionsList.value = true
            }, modifier = Modifier.align(CenterHorizontally)
        ) {
            Text(text = "Start")
        }

        if(updateQuestionsList.value){
            addQuestionsList(numberOfQuestions.value, viewModel)
        }
    }
}
@Composable
fun addQuestionsList(num : Int, viewModel: MainViewModel){

    val allQuestions by viewModel.allQuestions.observeAsState(listOf())
    println("checking info here")

    LazyColumn(
        Modifier.padding(24.dp)
    ){
        for (fruit in allQuestions){
            item {
                var answers = listOf(fruit.correctAnswer, fruit.incorrectAnswer1, fruit.incorrectAnswer2, fruit.incorrectAnswer3)
                Question(fruit.question, answers)
            }
        }

    }
}

/**
 * Determines the number of questions that will be displayed
 *
 * Info on EditText fields:
 * https://www.geeksforgeeks.org/edittext-in-android-using-jetpack-compose/
 *
 * Input validation:
 * https://stackoverflow.com/questions/13508346/regular-expression-for-integer-greater-than-0-and-less-than-11
 */
@Composable
fun numberInputField() {
    var input by remember { mutableStateOf("") } //The number of questions
    var regex = remember { Regex("^[1-9]\$|^10\$") }

    val onChange = { text: String ->
        if (text.isEmpty() || text.matches(regex)) {
            input = text
            try {
                var temp = input.toInt()

                numberOfQuestions.value = temp
                updateQuestionsList.value = false
            }catch (exception: Exception){
                exception.printStackTrace()
            }
        }
    }

    Column(
        horizontalAlignment = CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        CustomTextField(
            title = "Number of Questions",
            textState = input,
            onTextChange = onChange,
            keyboardType = KeyboardType.Text
        )
    }

}

/**
 * Template that constructs a question gui component
 *
 * Getting context
 * https://stackoverflow.com/questions/58743541/how-to-get-context-in-jetpack-compose
 */
@Composable
fun Question(QuestionStr : String, answers : List<String>) {


    //List of options. This represents the answers that will be given by the user
//    val options = listOf("A", "B", "C", "D")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(answers[2]) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally
    ) {
        Column {
            Text(
                text = QuestionStr,
                modifier = Modifier.align(CenterHorizontally)
            ) //The text entry for the question

            //Add a radio button for each option
            answers.forEach { text ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = (text == selectedOption),
                            onClick = { onOptionSelected(text) }
                        )

                        .padding(horizontal = 16.dp)
                ) {
                    val context = LocalContext.current
                    RadioButton(selected = (text == selectedOption),
                        modifier = Modifier.padding(8f.dp),
                        onClick = {
                            onOptionSelected(text)

                            Toast.makeText(context, text, Toast.LENGTH_LONG).show()
                        }
                    )

                    //The answer that goes along with this button
                    Text(
                        text = text,
                        modifier = Modifier.align(CenterVertically)
                    )
                }
            }
        }
    }
}


@Composable
fun loadQuestions(){

}

@Composable
fun initQuestions(viewModel: MainViewModel){
    val question1 = TrivialQuestion("When was UVM founded?", "1791", "1950", "1841", "1799")
    val question2 = TrivialQuestion("What are the UVM colors?", "Green and gold", "Red and blue", "Blue and white", "Black and green")
    val question3 = TrivialQuestion("What is the UVM mascot?", "Catamount", "Lion", "Panther", "Dog")
    val question4 = TrivialQuestion("Where is UVM located?", "Burlington Vermont", "LA, California", "Phoenix Arizona", "St Johnsbury, Vermont")
    val question5 = TrivialQuestion("What Lake is UVM located near?", "Lake Champlain", "Lake Willoughby", "Lake Bomoseen", "Lake Saint Catherine")
    val question6 = TrivialQuestion("What is the name of the main library?", "Howe", "Uris library", "Bobst library", "Mcquade library")
    val question7 = TrivialQuestion("What is the school motto?", "\"Studiis et Rebus Honestis\" — \"For studies and other honest pursuits\"", "“Mens agitat molem.\tMind moves matter.”", "“Lux sit. Let there be light.”", "“Crescat scientia; vita excolatur.Let knowledge increase; let life be enriched.”")
    val question8 = TrivialQuestion("What years are required to live on campus?", "First years", "Second years", "Third years", "A and B")
    val question9 = TrivialQuestion("How many campuses is UVM composed of?", "4", "5", "3", "2")
    val question10 = TrivialQuestion("How many residential complexes are there?", "9", "15", "5", "7")

    viewModel.clearQuestionsTable()

    viewModel.insertQuestion(question1)
    viewModel.insertQuestion(question2)
    viewModel.insertQuestion(question3)
    viewModel.insertQuestion(question4)
    viewModel.insertQuestion(question5)
    viewModel.insertQuestion(question6)
    viewModel.insertQuestion(question7)
    viewModel.insertQuestion(question8)
    viewModel.insertQuestion(question9)
    viewModel.insertQuestion(question10)
//    println("just inserted")

//    println("just got back info")
}


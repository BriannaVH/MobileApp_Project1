package com.group3.project4.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.group3.project4.MainViewModel
import com.group3.project4.TrivialQuestion


/**
 * Making private variables
 * https://stackoverflow.com/questions/65641635/jetpack-compose-how-can-we-call-a-composable-function-inside-an-onclick
 */
private var gradeButtonEnabled =  mutableStateOf(false)
private var questionsSelected = mutableListOf<TrivialQuestion>()
private var shouldShuffleAnswers =  mutableStateOf(false)
var answers = mutableMapOf<Int, List<String>>()
@Composable
fun Trivial(viewModel: MainViewModel) {
    var overallGrade by remember {mutableStateOf(0)}
    var showGrade by remember { mutableStateOf(false) }
    var buttonEnabled by remember { mutableStateOf(true) }
    var questionsAnswered by remember { mutableStateOf(Array(10) { ""})}
    var numQuestionsAnswered by remember { mutableStateOf(Array(1) {0}) }
    var displayQuestions by remember { mutableStateOf(false) }
    var numQuestions by remember { mutableStateOf(0) }
    var updateQuestionsList by remember { mutableStateOf(false) }
    var questions : List<TrivialQuestion>? = viewModel.allQuestions.observeAsState().value
    var showAnswers by remember { mutableStateOf(false)}
    var input by remember { mutableStateOf("") } //The number of questions



    val limit = 10
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
            Button(
                onClick = {
                    buttonEnabled = false
                    gradeButtonEnabled.value = false
                    shouldShuffleAnswers.value = false
                }, modifier = Modifier.align(CenterHorizontally),
                enabled = buttonEnabled
            ) {
                Text(text = "Load Questions",  style = MaterialTheme.typography.titleLarge,)
            }

            if (!buttonEnabled) {
                Column(
                    horizontalAlignment = CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {

                    CustomTextField(
                        title = "Number of Questions",
                        textState = input,
                        onTextChange = {
                            val regex = Regex("^[0-9]\$|^10\$")
                            if (it.isEmpty() || it.matches(regex)) {
                                input = it
                                try {
                                    if(it != ""){
                                        numQuestions = input.toInt()
                                    }
                                }catch (exception: Exception){
                                    //Invalid input. Don't do anything.
                                    numQuestions = 0
                                }
                            }

                            updateQuestionsList = false
                            shouldShuffleAnswers.value = false
                        },
                        keyboardType = KeyboardType.Number
                    )
                }


                Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                    Column {
                        Button(
                            onClick = {
                                showAnswers = false
                                gradeButtonEnabled.value = false
                                showGrade = false
                                shouldShuffleAnswers.value = false
                                overallGrade = 0
                                questionsAnswered = Array(10) { "" }
                                answers.clear()
                                numQuestionsAnswered[0] = 0
                                println("${questionsSelected.size} + ${questionsSelected}")
                                if (questionsSelected != null) {
                                    println("size before dropping " + questionsSelected.size)

                                    var numToDrop = 10 - numQuestions

                                    if (questions != null) {
                                        questions = questions!!.shuffled()
                                        questions = questions!!.drop(numToDrop)
                                        println("size of questions ${questions!!.size}")
                                        questionsSelected = questions!!.toMutableList()
                                        println("AFTER ${questionsSelected.size} + ${questionsSelected}")
                                    }
                                }


                                if (questionsSelected != null) {
                                    println("size of dropped array: " + questionsSelected.size)
                                    displayQuestions = true
                                    updateQuestionsList = true

                                    for (q in questionsSelected) {
                                        println("q: $q")
                                        var answer = listOf(
                                            q.correctAnswer,
                                            q.incorrectAnswer1,
                                            q.incorrectAnswer2,
                                            q.incorrectAnswer3
                                        ).shuffled()
                                        answers[questionsSelected.indexOf(q)] = answer
                                    }
                                }

                            }
                        ) {
                            Text(text = "Go", style = MaterialTheme.typography.titleMedium)
                        }
                    }


                    println("Re-rendering grade button")
                    Column (modifier = Modifier.padding(start = 5.dp, end = 5.dp)) {
                        Button(
                            onClick = {
                                showAnswers = true
                                overallGrade = gradeTrivial(questionsAnswered, questionsSelected)
                                showGrade = true
                            },
                            enabled = gradeButtonEnabled.value
                        ) {
                            Text(text = "Grade", style = MaterialTheme.typography.titleMedium)
                        }
                    }
                    println("Re-rendering grade button")
                    Column  {
                        Button(
                            onClick = {
                                input = ""
                                showAnswers = false
                                gradeButtonEnabled.value = false
                                showGrade = false
                                shouldShuffleAnswers.value = false
                                overallGrade = 0
                                questionsAnswered = Array(10) { "" }
                                answers.clear()
                                numQuestionsAnswered[0] = 0
                            },
                            enabled = true
                        ) {
                            Text(text = "Reset", style = MaterialTheme.typography.titleMedium)
                        }
                    }
                }






                //If the showGrade flag is enabled...
                if(showGrade){
                    Text(
                        text = " You scored: ${overallGrade} out of $numQuestions questions",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                            .align(CenterHorizontally)
                            .padding(top = 5.dp)
                    ) //Display the overallGrade value
                }

                println("answers: $answers")
                if(displayQuestions){

                    if(!updateQuestionsList){
                        return
                    }

//
                    /**
                     * https://stackoverflow.com/questions/68164883/how-do-i-create-a-jetpack-compose-column-where-a-middle-child-is-scrollable-but
                     * ^ Making the column scrollable
                     */
                    Column(
                        Modifier
                            .padding(24.dp)
                            .verticalScroll(rememberScrollState())
                    ){
                        // change this so that if checks if the questions have been specified yet
                        // if not, do this, otherwise check what we have already
                        if (questionsSelected != null) {
                            println("questions are shuffled and displayed")
//                            Question(q.question, answers[], questionsSelected.indexOf(q), questionsAnswered, numQuestionsAnswered, numQuestions, q.correctAnswer, showAnswers)
                            // loop through each question and make a question for it

                            for(i in answers){
                            Question(questionsSelected[i.key].question, i.value, i.key, questionsAnswered, numQuestionsAnswered, numQuestions, questionsSelected[i.key].correctAnswer, showAnswers)
                            }
                        }
                    }
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
/**
 * Template that constructs a question gui component
 *
 * Getting context
 * https://stackoverflow.com/questions/58743541/how-to-get-context-in-jetpack-compose
 */
@Composable
fun Question(QuestionStr : String, answers : List<String>, id: Int, questionsAnswered: Array<String>, numQuestionsAnswered: Array<Int>, numQuestions: Int, correctAnswer: String, showAnswers: Boolean) {

    //List of options. This represents the answers that will be given by the user
//    val options = listOf("A", "B", "C", "D")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(questionsAnswered[id]) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally,

    ) {
        Column {
            Text(
                text = QuestionStr,
                style  = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = 30.dp)
            ) //The text entry for the question

            //Add a radio button for each option
            answers.forEach { text ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = (text == selectedOption),
                            onClick = {
                                onOptionSelected(text)
                                println("onclick triggered")
                                println("new option selected " + text)
                                println("previously: " + questionsAnswered[id])
                                if (questionsAnswered[id] == "") {
                                    questionsAnswered[id] = text
                                    numQuestionsAnswered[0] += 1
                                    println("one step closer to being graded " + numQuestionsAnswered[0])
                                    // change to be the actual number of questions later on
                                    println("check num answered " + numQuestionsAnswered[0])
                                    println("check num needed " + numQuestions)
                                    if (numQuestionsAnswered[0] === numQuestions) {
                                        println("all questions answered")
                                        gradeButtonEnabled.value = true
                                    }
                                } else {
                                    println("this was already answered before")
                                    questionsAnswered[id] = text
                                }
                            }
                        )

                        .padding(horizontal = 16.dp)
                ) {
                    val context = LocalContext.current
                    RadioButton(
                        selected = (text == selectedOption),
                        modifier = Modifier.padding(8f.dp),
                        onClick = {
                            println("onclick triggered")
                            println("new option selected " + text)
                            println("previously: " + questionsAnswered[id])
                            if (questionsAnswered[id] == ""){
                                questionsAnswered[id] = text
                                numQuestionsAnswered[0] += 1
                                println("one step closer to being graded " + numQuestionsAnswered[0])
                                // change to be the actual number of questions later on
                                println("check num answered " + numQuestionsAnswered[0])
                                println("check num needed " + numQuestions)
                                if (numQuestionsAnswered[0] === numQuestions) {
                                    println("all questions answered")
                                    gradeButtonEnabled.value = true
                                }
                            }
                            else {
                                println("this was already answered before")
                                questionsAnswered[id] = text
                            }
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
            if (showAnswers){
                Text(
                    text = "Correct Answer: " + correctAnswer,
                    modifier = Modifier.align(CenterHorizontally),
                    color = MaterialTheme.colorScheme.primary
                )
            }

        }
    }
}

fun gradeTrivial(questionsAnswered: Array<String>, questions: List<TrivialQuestion>?) : Int{
    println("grade trivial")
    var numberCorrect = 0
    if (questions != null) {
        for (veggie in questions){
            println("they answered: " + questionsAnswered[questions.indexOf(veggie)])
            println("correct answer: " + veggie.correctAnswer)
            if (questionsAnswered[questions.indexOf(veggie)] == veggie.correctAnswer){
                numberCorrect += 1
            }
        }
        println("they got " + numberCorrect + " out of " + questions.size + " correct")
    }

    return numberCorrect
}

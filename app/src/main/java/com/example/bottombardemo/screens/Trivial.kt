package com.example.bottombardemo.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
@Preview
fun Trivial() {

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
                /*TODO*/
            }, modifier = Modifier.align(CenterHorizontally)

        ) {
            Text(text = "Start")
        }
        
        Question(QuestionStr = "Example question 1")


    }
}

/**
 * Determines the number of questions that will be displayed
 *
 * https://www.geeksforgeeks.org/edittext-in-android-using-jetpack-compose/
 * https://stackoverflow.com/questions/13508346/regular-expression-for-integer-greater-than-0-and-less-than-11
 */
@Composable
fun numberInputField() {
    var input by remember { mutableStateOf("") }
    var regex = remember { Regex("^[1-9]\$|^10\$") }

    val onChange = { text: String ->
        if (text.isEmpty() || text.matches(regex)) {
            input = text
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
fun Question(QuestionStr : String) {


    //List of options. This represents the answers that will be given by the user
    val options = listOf("A", "B", "C", "D")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(options[2]) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally
    ) {
        Column {
            Text(text = QuestionStr,
                modifier = Modifier.align(CenterHorizontally)) //The text entry for the question

            //Add a radio button for each option
            options.forEach { text ->
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

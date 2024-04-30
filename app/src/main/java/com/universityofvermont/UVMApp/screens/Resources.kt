package com.universityofvermont.UVMApp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Resources() {

    val resources = arrayOf(
        arrayOf(
            "UNDERGRADUATE WRITING CENTER",
            "Academic support is only a click or call away! From reviewing assignments, to getting organized and citing sources, peer writing tutors are here to assist in the writing process.",
            "https://www.uvm.edu/undergradwriting"
        ),
        arrayOf(
            "TUTORING CENTER",
            "From supplemental tutoring, to study skills and subject area tutoring, all students receive free services at UVM. What does that look like for you?.",
            "https://www.uvm.edu/academicsuccess/tutoring_center"
        ),
        arrayOf(
            "ADVISING AT UVM",
            "With both Academic Advisors and Dean's Office Staff, UVM students have access to comprehensive academic advising -- and with an app to contact advisors, access has never been easier.",
            "https://www.uvm.edu/academicsuccess/advising"
        ),
        arrayOf(
            "UVM HOWE LIBRARY",
            "The UVM Howe Library offers a multitude of services including access to library materials, study areas, multimedia services, and classroom technology services and more.",
            "https://library.uvm.edu/help/visiting_the_howe_library"
        ),
        arrayOf(
            "STUDENT ACCESSIBILITY SERVICES",
            "Questions about UVM accessibility accommodations or available services? We've got a team for that.",
            "https://www.uvm.edu/academicsuccess/student_accessibility_services"
        ),
        arrayOf(
            "REGISTRATION INFORMATION",
            "There is a lot to keep track of when registering for classes-- from degree audits to the UVM registration schedule and account holds, we'll help you stay on track. For all other questions visit:",
            "https://www.uvm.edu/provost/academic-faqs-fall-2020"
        ),
        arrayOf(
            "CAREER CENTER",
            "Each path to career success is unique. The Career Center is here to help you find yours. Connect with employers, jobs, internships, events, and more. Explore their website to begin your journey.",
            "https://www.uvm.edu/career"
        ),
        arrayOf(
            "OFFICE OF FELLOWSHIPS, OPPORTUNITIES, & UNDERGRADUATE RESOURCES",
            "Explore resources and information about research and scholarly engagement -- both in your community and beyond.",
            "https://www.uvm.edu/four"
        ),
        arrayOf(
            "OFFICE OF COMMUNITY-ENGAGED LEARNING",
            "Learn about academic service-learning through courses, research, and internships. Be a part of the global Catamount experience.",
            "https://www.uvm.edu/celo/community-engaged-learning-opportunities"
        ),
        arrayOf(
            "STUDENT ATHLETE ACADEMIC SUPPORT",
            "The Student-Athlete Development staff provides comprehensive academic support and services for UVM student-athletes, and is also home to a strong community of student leaders.",
            "https://uvmathletics.com/sports/2011/9/20/SAD_0920110830.aspx"
        )
    )

    val results = mutableListOf<Array<String>>()

    var resourceName by remember { mutableStateOf("") }

    val onTextChange = { text : String ->
        resourceName = text
    }

    Box(
        modifier = Modifier
            .wrapContentWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp)
            ,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Search(
                title = "Campus Resources",
                textState = resourceName,
                onTextChange = onTextChange,
                keyboardType = KeyboardType.Text
            )
        }
    }

    LazyColumn(Modifier
        .padding(top = 100.dp)
    ) {
        resources.forEach { nestedArray ->
            if (nestedArray.any { it.contains(resourceName, ignoreCase = true) }) {
                results.add(nestedArray)
            }
        }
        items(results) { item ->
            val name = item[0]
            val resourceContent = item[1]
            val resourceLink = item[2]

            HorizontalDivider(modifier = Modifier.padding(top = 6.dp))

            campusResourceCard(
                name,
                resourceLink,
                resourceContent
            )
        }
    }
}

@Composable
fun Search(
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
        modifier = Modifier
            .padding(10.dp)
            .width(325.dp),
        textStyle = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp)
    )
}

@Composable
fun campusResourceCard(
    headerString: String,
    link: String,
    descriptionString: String
) {

    //https://stackoverflow.com/questions/65567412/jetpack-compose-text-hyperlink-some-section-of-the-text

    //Function to build a string that has a link attached to it
    val annotatedLinkString: AnnotatedString = buildAnnotatedString {

        //Indices determine where link starts/ends in the word
        val startIndex = 0
        val endIndex = headerString.length
        append(headerString)

        //Styling for text with link
        addStyle(
            style = SpanStyle(
                color = Color.White,
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
                fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                letterSpacing = MaterialTheme.typography.titleLarge.letterSpacing
            ), start = startIndex, end = endIndex
        )

        // attach a string annotation that stores a URL to the text "link"
        addStringAnnotation(
            tag = "URL",
            annotation = link,
            start = startIndex,
            end = endIndex
        )

    }

    val uriHandler = LocalUriHandler.current


    //Card for the entire
    Card (
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .border(.55.dp, Color.Black, shape = RoundedCornerShape(8))
    ){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(color = MaterialTheme.colorScheme.primary),
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    // 🔥 Clickable text returns position of text that is clicked in onClick callback
                    ClickableText(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally),
                        text = (annotatedLinkString),
                        style = TextStyle(
                            textAlign = TextAlign.Center),
                        onClick = {
                            annotatedLinkString
                                .getStringAnnotations("URL", it, it)
                                .firstOrNull()?.let { stringAnnotation ->
                                    uriHandler.openUri(stringAnnotation.item)
                                }
                        }
                    )
                }
            }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically,

        )
        {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                HorizontalDivider()
                Text(
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(16.dp),
                    text = descriptionString,
                    textAlign = TextAlign.Center
                )

            }
        }
    }
}


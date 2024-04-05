package com.group3.project4.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.group3.project4.R

@Composable
fun Schools() {
    Column (modifier = Modifier
        .wrapContentWidth()
        .verticalScroll(rememberScrollState())) {
        Text(
            text = "Academic Colleges and Schools Of UVM",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(4.dp),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        val ALSName = "College of Agriculture And Life Sciences"
        val ALSDesc = "The College of Agriculture and Life Sciences offers dynamic programs in the life and social sciences, with a focus on nutrition and food science, human and animal health, communication, entrepreneurship, sustainable and resilient communities, and the complex web of food systems. Explore our diverse portfolio of majors: Biochemistry, Community and International Development, Plant and Animal Sciences, Biology, Food Systems, Public Communication, Microbiology and Molecular Genetics to name a few.  Our rich array of internships and research experiences empowers students to become doers. We challenge students to push the boundaries of creativity and innovation, imparting the knowledge, skills, and values to become the next generation of informed citizens, thinkers and leaders. Our sciences are grounded in humanity for students who want to make a difference in the world."

        val ASName = "College of Art and Sciences"
        val ASDesc = "At UVM’s College of Arts and Sciences, you'll have extraordinary access to distinguished scholars and scientists, in and out of the classroom; 40 percent of our students engage in faculty-mentored research. You'll also gain experience through engaging internships, service learning, or study abroad experiences, taking advantage of UVM partnerships throughout Vermont, the U.S., and abroad. While building knowledge and skills in your area of study, you’ll learn how to analyze data, identify connections, and communicate complex ideas – abilities employers want and society needs."

        val ESSName = "College of Education and Social Services"
        val ESSDesc = "We strive to create a more humane and just society that maximizes human potential and the quality of life for all. Students in UVM's College of Education and Social Services (CESS) receive outstanding preparation for rewarding and impactful careers in education, social work and human services. Our faculty and alumni play leading roles in transformative teaching, research, policy development and service collaborations across Vermont, throughout the nation, and around the world."

        val EMSName = "College of Engineering and Mathematical Sciences"
        val EMSDesc = "At the College of Engineering and Mathematical Sciences, we view our role as much larger than educators. We are a community of problem solvers, inspiring each other to build true world solutions that will lead to healthy, sustainable societies.  Our approach is personal, fostering a culture of collaboration that’s as driven by student-to-student connections as by our world-class faculty and advanced research opportunities.  You may enter UVM a little unsure, eager and excited for the next chapter. By the time you’re ready to leave, you’ll be poised to change the world."

        val NHSName = "College of Nursing and Health Sciences"
        val NHSDesc = "In the College of Nursing and Health Sciences, we’re setting the new standard of care. We believe that the best health professionals will not only have deep experience in their fields of study, but also broad exposure across disciplines. At UVM, you’ll have access to top scholars and courses, and you’ll live and study alongside friends with diverse passions and majors whose ideas and interests will expand your world. With this deep and broad education, you’ll launch prepared not only to land a great job, but with the ability to see the world through multiple lenses, an essential 21st century life skill.   CNHS offers undergraduate, graduate, and certificate education in Communication Sciences and Disorders, Exercise Science, Integrative Health, Medical Laboratory Science, Medical Radiation Sciences, Nursing, Occupational Therapy, Physical Activity and Wellness, Physical Therapy, Speech-Language Pathology, and Public Health."


        ExpandableTextCard(headerString = ALSName,
            descriptionString = ALSDesc,
            R.drawable.als)
        ExpandableTextCard(headerString = ASName,
            descriptionString = ASDesc,
            R.drawable.`as`)
        ExpandableTextCard(headerString = ESSName,
            descriptionString = ESSDesc,
            R.drawable.ess)
        ExpandableTextCard(headerString = EMSName,
            descriptionString = EMSDesc,
            R.drawable.ems)
        ExpandableTextCard(headerString = NHSName,
            descriptionString = NHSDesc,
            R.drawable.nhs)
    }

}

/**
 * Source:
 * https://medium.com/@acceldia/jetpack-compose-creating-expandable-cards-with-content-9ea1eae09efe
 *
 * Expandable card that only supports text
 */
@Composable
fun ExpandableTextCard(headerString: String, descriptionString: String, imageId: Int){
    var isExpanded by remember { mutableStateOf(false) }

    Card (
        shape = RoundedCornerShape(8.dp),

        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable(
                onClick = {
                    isExpanded = !isExpanded
                }
            ),
    ){
        Column {
            Row( modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center) {
                Text(
                    text = headerString,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(8.dp),
                    textAlign = TextAlign.Center
                )
            }

            if(isExpanded){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = imageId),
                        contentDescription = "Your Image Description",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(10.dp)
                            .size(250.dp)
                    )
                    Text(
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(25.dp),
                        text = descriptionString,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}
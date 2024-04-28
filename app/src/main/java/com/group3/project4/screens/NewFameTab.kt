package com.group3.project4.screens


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.group3.project4.R
import kotlinx.coroutines.launch

/**
 * https://medium.com/@2018.itsuki/android-kotlin-jetpack-compose-bottom-thing-bottom-b050019f0948
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NewFame() {
    val pagerState = rememberPagerState(pageCount = {
        10
    })

    val coroutineScope = rememberCoroutineScope()

    Column (
        modifier = Modifier
            .fillMaxSize()
    ){

        //top row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){

        }

        //middle row
        Row(
            modifier = Modifier
//                .background(Color.Green)
                .fillMaxWidth()
                .weight(0.5f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            HorizontalPager(
                state = pagerState,
                modifier =  Modifier
                    .fillMaxWidth()
            ) { page ->
                when(page){
                    0 -> {

                        val ALSDesc = "The College of Agriculture and Life Sciences offers dynamic programs in the life and social sciences, with a focus on nutrition and food science, human and animal health, communication, entrepreneurship, sustainable and resilient communities, and the complex web of food systems. Explore our diverse portfolio of majors: Biochemistry, Community and International Development, Plant and Animal Sciences, Biology, Food Systems, Public Communication, Microbiology and Molecular Genetics to name a few.  Our rich array of internships and research experiences empowers students to become doers. We challenge students to push the boundaries of creativity and innovation, imparting the knowledge, skills, and values to become the next generation of informed citizens, thinkers and leaders. Our sciences are grounded in humanity for students who want to make a difference in the world."

                        PageCard(
                            title = "Test Title",
                            description = ALSDesc,
                            id = R.drawable.uvmlogo
                        )
                    }

                    else -> {
                        //Don't do anything here.
                    }
                }
            }
        }

        //bottom row
        Row(
            modifier = Modifier
                .background(Color.Blue)
                .fillMaxWidth()
                .height(50.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            //Left arrow
            IconButton(
                modifier = Modifier
                    .weight(1f)
                    .alpha(4f)
                    .rotate(90f),
                onClick = {
                    println("Left arrow clicked")

                    if(pagerState.canScrollBackward){
                        coroutineScope.launch {
                            // Call scroll to on pagerState
                            pagerState.scrollToPage(pagerState.currentPage - 1)
                        }
                    }

                }) {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,

                    contentDescription = "Drop-Down Arrow"
                )
            }

            Row (
                modifier = Modifier
                .background(Color.Green)
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .weight(0.5f),
            ){

            }

            //Right arrow
            IconButton(
                modifier = Modifier
                    .weight(1f)
                    .alpha(4f)
                    .rotate(-90f),
                onClick = {
                    if(pagerState.canScrollForward){
                        coroutineScope.launch {
                            // Call scroll to on pagerState
                            pagerState.scrollToPage(pagerState.currentPage + 1)
                        }
                    }
                }) {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,

                    contentDescription = "Left Arrow"
                )
            }
        }
    }
}

@Composable
fun PageCard(title : String, description: String, id: Int){
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                onClick = {
                }
            ),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(15.dp)
        ) {


            //Image
            Image(
                painter = painterResource(id = id),
                contentDescription = "",
                contentScale = ContentScale.Fit,
                modifier = Modifier
//                    .padding(15.dp)
                    .size(250.dp)
            )

            // Title / Header text
            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(4.dp),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )


            //Description text
            Text(
                text = description,

            )

        }
    }
}
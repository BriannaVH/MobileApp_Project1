package com.group3.project4.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.group3.project4.R
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NewFame() {
    val pagerState = rememberPagerState(pageCount = {
        4
    })
    HorizontalPager(
        state = pagerState,
        modifier =  Modifier
            .fillMaxSize()
    ) { page ->
        when(page){
            0 -> {
                AchievementsPage(
                    title = "H. H. Holmes",
                    description = "Herman Webster Mudgett, better known as Dr. Henry Howard Holmes or H. H. Holmes, was an American con artist and serial killer active between 1891 and 1894. By the time of his execution in 1896, Holmes had engaged in a lengthy criminal career that included insurance fraud, forgery, swindling, three to four bigamous illegal marriages, horse theft and murder.",
                    id = R.drawable.hhh
                )
            }

            else -> {
                //Don't do anything here.
            }
        }
    }
}

@Composable
fun AchievementsPage(title : String, description: String, id: Int){
    PageCard(title, description, id)
}


@Composable
fun PageCard(title : String, description: String, id: Int){
    Card(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = title,
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .padding(16.dp),
                textAlign = TextAlign.Start,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            Image(
                painter = painterResource(id = id),
                contentDescription = "",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .padding(15.dp)
                    .size(250.dp)
            )

            Text(description)

        }
    }
}
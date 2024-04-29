package com.universityofvermont.UVMApp.screens

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.universityofvermont.UVMApp.R
import kotlinx.coroutines.launch

@Composable
fun Fame() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentWidth()
            .verticalScroll(rememberScrollState())
    )
        {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Famous Achievements",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(top = 15.dp, bottom = 1.dp)
                )

                FameEntry(
                    "National Ranking",
                    "University of Vermont is ranked 133 out of 439 schools nationally.",
                    R.drawable.uvmlogo
                )
                FameEntry(
                    "Skiing Championship",
                    "UVM is a six-time national champion in skiing.",
                    R.drawable.skiing
                )
                FameEntry(
                    "Varsity Teams",
                    "UVM has 18 varsity teams that compete at the NCAA Division level I.",
                    R.drawable.uvmcat
                )
                FameEntry(
                    "Associations",
                    "The UVM Catamounts are members of the American East Conference, the Hockey East Association, and the Eastern Intercollegiate Ski Association.",
                    R.drawable.aec
                )
                FameEntry(
                    "Academic Cup",
                    "UVM won the first Academic Cup in 1996, with 8 wins overall. It is the only school in the American East that has won the Academic Cup seven years in a row (2005-2011).",
                    R.drawable.academiccup
                )
                Text(
                    "Famous People",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(top = 15.dp, bottom = 1.dp)
                )
                FameEntry(
                    "Jody Williams",
                    "Jody Williams was a Nobel Peace Prize Laureate in 1997. She was awarded it because she worked towards banning and clearing landmines. By 1997, thanks to her strength and organizational talent, the International Campaign to Ban Landmines (ICBL) had 1,000 organizations from 60 countries on its list of members. The Ottawa Convention, which was signed by 120 states and entered into force in 1999, will always be associated with the names of Jody Williams and the ICBL. It banned the use, production, sale and stock-piling of anti-personnel mines. In addition it contained provisions concerning mine clearance and the obligation to provide humanitarian assistance.",
                    R.drawable.jw
                )
                FameEntry(
                    "Henry Jarvis Raymond",
                    "Henry was was an American journalist, newspaper publisher, and politician who co-founded both the Republican Party and The New York Times. He was a member of the New York State Assembly, the lieutenant governor of New York, Chairman of the Republican National Committee, and elected to the US House of Representatives. For his contribution towards the formation of the Republican Party,[1] Raymond has sometimes been called the godfather of the Republican Party.",
                    R.drawable.hjr
                )
                FameEntry(
                    "Barbara Cochran",
                    "Barbara Ann Cochran is a former World Cup alpine ski racer and Olympic gold medalist from the United States. Born in Claremont, New Hampshire, Cochran was the second of four siblings of the famous \"Skiing Cochrans\" family of Richmond, Vermont, which has operated a small ski area in their backyard since 1961. Cochran published her book Skiing for Women and she eventually became a writer for The Washington Post.",
                    R.drawable.bc
                )
                FameEntry(
                    "H. H. Holmes",
                    "Herman Webster Mudgett, better known as Dr. Henry Howard Holmes or H. H. Holmes, was an American con artist and serial killer active between 1891 and 1894. By the time of his execution in 1896, Holmes had engaged in a lengthy criminal career that included insurance fraud, forgery, swindling, three to four bigamous illegal marriages, horse theft and murder.",
                    R.drawable.hhh
                )

                Text(
                    "History",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(top = 15.dp, bottom = 1.dp)
                )
                History()


            }





        }



}

/**
 * personName --> The name of the individual
 * description --> The description of the individual
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FameEntry(personName : String, description : String, id: Int){

        var showDialog by remember { mutableStateOf(false) }
        var changeDialog = { showDialog = false }

        if (showDialog) {
            // I'm pretty sure this line is what we need to implement the crossfade but
            // I'm not quite sure how to fix it so that the parameters are accepted
            // I think that I remember something similar coming up before where we turned
            // mutable variables into arrays in order to change them but I'm not 100% sure
            // https://stackoverflow.com/questions/71006883/is-jetpack-compose-crossfade-just-a-subset-functionality-of-animatecontent
            // https://foso.github.io/Jetpack-Compose-Playground/animation/crossfade/

            Crossfade(targetState = showDialog, label = "", animationSpec = tween(10000)) { shown ->
                print(shown)
                CustomDialog(onDismissRequest = changeDialog , personName, description, id)
            }
        }
            FlowRow(
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 10.dp) //padding between box and rest of screen
                    .border(.55.dp, Color.Black, shape = RoundedCornerShape(20))
                    .padding(top = 5.dp, bottom = 5.dp, start = 10.dp, end = 10.dp), // padding for inside of the box
                horizontalArrangement = Arrangement.Center

            ) {
                Column(
                    modifier = Modifier
                        .width(200.dp)
                        .align(Alignment.CenterVertically),
                    horizontalAlignment = Alignment.CenterHorizontally // This will center all children horizontally
                ) {
                    Text(
                        personName,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp), // Ensure it takes up all horizontal space
                        )
                }
                Column(modifier = Modifier
                    .align(Alignment.CenterVertically)
                ) {
                    Button(onClick = { showDialog = true }) {
                        Text(text = "Learn More...",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier
                                .height(20.dp)
                                .wrapContentHeight(align = Alignment.CenterVertically),
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }

}


@Composable
fun CustomDialog(onDismissRequest: () -> Unit, individualName : String, description: String, id: Int) {
    val scrollState = rememberScrollState()
    AlertDialog(
        onDismissRequest = { onDismissRequest() },
        title = {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = individualName,
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center,
                ) },
        text = {

            Column(modifier = Modifier.verticalScroll(scrollState)) {
                Image(
                    painter = painterResource(id = id),
                    contentDescription = "Your Image Description",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .padding(10.dp)
                        .align(Alignment.CenterHorizontally)
                        .size(220.dp)
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight() // Ensures the text fills the horizontal space
                )
            }
        },
        confirmButton = {
            Button(
                onClick = { onDismissRequest() }
            ) {
                Text("Close")
            }
        }
    )
}

@Composable
fun PageCards(title: String, description: String, id: Int) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .size(475.dp)
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Image(
                painter = painterResource(id = id),
                contentDescription = "Image for $title",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(250.dp)
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
                textAlign = TextAlign.Center
                )

        }
    }
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun History() {
    var pagerState = rememberPagerState(pageCount = {5}, initialPage = 2)
    val coroutineScope = rememberCoroutineScope()

    Column() {
        // Horizontal Pager
        HorizontalPager(
            state = pagerState,
            verticalAlignment = Alignment.Top,
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
        ) {
            page ->
            when (page) {
                0 -> {
                    PageCards(
                        title = "1791",
                        description = "Chartered the same year that Vermont became the 14th state.",
                        id = R.drawable.state
                    )
                }
                1 -> {
                    PageCards(
                        title = "1824",
                        description = "The citizens of Burlington helped fund the university's first building, and, when fire destroyed it in 1824, also paid for its replacement, the Old Mill building.",
                        id = R.drawable.mill
                    )
                }
                2 -> {
                    PageCards(
                        title = "1862",
                        description = "Although it began as a private university, UVM attained quasi-public status with the passage of the Morrill Land-Grant College Act in 1862 and the addition of the State Agricultural College.",
                        id = R.drawable.sac
                    )
                }
                3 -> {
                    PageCards(
                        title = "1871",
                        description = "The university was an early advocate of both women's and African-Americans' participation in higher education.",
                        id = R.drawable.edu
                    )
                }
                4 -> {
                    PageCards(
                        title = "1877",
                        description = "In 1877, Phi Beta Kappa initiated the first African-American into the society.",
                        id = R.drawable.pbk
                    )
                }
                else -> {
                    Text("Page $page")
                }
            }
        }

        // Navigation arrows
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    if (pagerState.currentPage > 0) {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        }
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Previous Page"
                )
            }

            Spacer(modifier = Modifier.weight(1f))  // This spacer distributes space evenly between the icons

            IconButton(
                onClick = {
                    if (pagerState.currentPage < pagerState.pageCount - 1) {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Next Page"
                )
            }
        }
    }
}

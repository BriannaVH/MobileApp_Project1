package com.example.bottombardemo.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.bottombardemo.R
import com.example.bottombardemo.ui.theme.BottomBarDemoTheme

@Composable
fun Fame() {

    Column(
        modifier = Modifier
            .fillMaxSize()

    ) {
        Column(modifier = Modifier
            .wrapContentWidth()
            .align(alignment = Alignment.CenterHorizontally)
            .verticalScroll(rememberScrollState())
        )
        {
            Text("Famous Achievements", fontWeight = FontWeight.Bold)
            FameEntry(
                "National Ranking",
                "University of Vermont is ranked 133 out of 439 schools nationally.",
                R.drawable.uvmlogo)
            FameEntry(
                "Skiing Championship",
                "UVM is a six-time national champion in skiing.",
                R.drawable.skiing)
            FameEntry(
                "Varsity Teams",
                "UVM has 18 varsity teams that compete at the NCAA Division level I.",
                R.drawable.uvmcat)
            FameEntry(
                "Associations",
                "The UVM Catamounts are members of the American East Conference, the Hockey East Association, and the Eastern Intercollegiate Ski Association.",
                R.drawable.aec)
            FameEntry(
                "Academic Cup",
                "UVM won the first Academic Cup in 1996, with 8 wins overall. It is the only school in the American East that has won the Academic Cup seven years in a row (2005-2011).",
                R.drawable.academiccup)
            Text("Famous People", fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
            FameEntry("Jody Williams",
                "Jody Williams was a Nobel Peace Prize Laureate in 1997. She was awarded it because she worked towards banning and clearing landmines. By 1997, thanks to her strength and organizational talent, the International Campaign to Ban Landmines (ICBL) had 1,000 organizations from 60 countries on its list of members. The Ottawa Convention, which was signed by 120 states and entered into force in 1999, will always be associated with the names of Jody Williams and the ICBL. It banned the use, production, sale and stock-piling of anti-personnel mines. In addition it contained provisions concerning mine clearance and the obligation to provide humanitarian assistance.",
                R.drawable.jw)
            FameEntry(
                "Henry Jarvis Raymond", "Henry was was an American journalist, newspaper publisher, and politician who co-founded both the Republican Party and The New York Times. He was a member of the New York State Assembly, the lieutenant governor of New York, Chairman of the Republican National Committee, and elected to the US House of Representatives. For his contribution towards the formation of the Republican Party,[1] Raymond has sometimes been called the godfather of the Republican Party.",
                R.drawable.hjr)
            FameEntry(
                "Barbara Cochran", "Barbara Ann Cochran is a former World Cup alpine ski racer and Olympic gold medalist from the United States. Born in Claremont, New Hampshire, Cochran was the second of four siblings of the famous \"Skiing Cochrans\" family of Richmond, Vermont, which has operated a small ski area in their backyard since 1961. Cochran published her book Skiing for Women and she eventually became a writer for The Washington Post.",
                R.drawable.bc)
            FameEntry(
                "H. H. Holmes",
                "Herman Webster Mudgett, better known as Dr. Henry Howard Holmes or H. H. Holmes, was an American con artist and serial killer active between 1891 and 1894. By the time of his execution in 1896, Holmes had engaged in a lengthy criminal career that included insurance fraud, forgery, swindling, three to four bigamous illegal marriages, horse theft and murder.",
                R.drawable.hhh)
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
        if (showDialog) {
            CustomDialog(onDismissRequest = { showDialog = false}, personName, description, id)
        }
            FlowRow(
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 10.dp,) //padding between box and rest of screen
                    .border(.55.dp, Color.Black, shape = RoundedCornerShape(20))
                    .padding(top = 5.dp, bottom = 5.dp, start = 5.dp), // padding for inside of the box
                horizontalArrangement = Arrangement.Center

            ) {
                Column(modifier = Modifier
                    .width(200.dp)
                    .align(Alignment.CenterVertically)
                ){
                    Text(personName)
                }
                Column(modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(end = 5.dp)){
                    Button(onClick = { showDialog = true }) {
                        Text(text = "Learn More...",
                            modifier = Modifier
                                .height(20.dp)
                                .wrapContentHeight(align = Alignment.CenterVertically),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

}


@Composable
fun CustomDialog(onDismissRequest: () -> Unit, individualName : String, description: String, id: Int) {
    AlertDialog(
        onDismissRequest = { onDismissRequest() },
        title = { Text(text = individualName) },
        text = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(description)
                Image(
                    painter = painterResource(id = id),
                    contentDescription = "Your Image Description",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .padding(15.dp)
                        .size(250.dp)
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

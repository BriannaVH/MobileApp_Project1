package com.example.bottombardemo.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.bottombardemo.R

@Composable
fun Fame() {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(modifier = Modifier
            .wrapContentWidth()
            .align(Alignment.TopCenter)
        )
        {
            Text("Famous Achievements", fontWeight = FontWeight.Bold)
            FameEntry("Person asndkjdnka 2", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse massa nunc, eleifend efficitur turpis sed, finibus convallis erat. Vestibulum lacinia fringilla diam vitae volutpat. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Fusce nec efficitur velit, placerat luctus mi. Donec ultricies turpis in quam aliquet convallis. Aliquam egestas tortor nec euismod volutpat. Integer congue cursus dolor, non ornare felis luctus sit amet. Nunc et elit tempus, consequat nisi eu, facilisis augue. Phasellus in leo mi. Nunc non iaculis dui. Ut eu libero in velit porttitor ullamcorper quis sed ante. Pellentesque sodales justo vel ante varius, non consectetur quam feugiat. Morbi vitae dui dapibus, pharetra diam sed, feugiat neque. Cras pharetra urna diam, luctus vulputate dui interdum a. Curabitur ac orci et velit imperdiet finibus.")
            Text("Famous People", fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
            FameEntry("Jody Williams", "Jody Williams was a Nobel Peace Prize Laureate in 1997. She was awarded it because she worked towards banning and clearing landmines. By 1997, thanks to her strength and organizational talent, the International Campaign to Ban Landmines (ICBL) had 1,000 organizations from 60 countries on its list of members. The Ottawa Convention, which was signed by 120 states and entered into force in 1999, will always be associated with the names of Jody Williams and the ICBL. It banned the use, production, sale and stock-piling of anti-personnel mines. In addition it contained provisions concerning mine clearance and the obligation to provide humanitarian assistance.")
            FameEntry("Henry Jarvis Raymond", "Henry was was an American journalist, newspaper publisher, and politician who co-founded both the Republican Party and The New York Times. He was a member of the New York State Assembly, the lieutenant governor of New York, Chairman of the Republican National Committee, and elected to the US House of Representatives. For his contribution towards the formation of the Republican Party,[1] Raymond has sometimes been called the godfather of the Republican Party.")
            FameEntry("Barbara Cochran", "Barbara Ann Cochran is a former World Cup alpine ski racer and Olympic gold medalist from the United States. Born in Claremont, New Hampshire, Cochran was the second of four siblings of the famous \"Skiing Cochrans\" family of Richmond, Vermont, which has operated a small ski area in their backyard since 1961. Cochran published her book Skiing for Women and she eventually became a writer for The Washington Post.")
            FameEntry("H. H. Holmes", "Herman Webster Mudgett, better known as Dr. Henry Howard Holmes or H. H. Holmes, was an American con artist and serial killer active between 1891 and 1894. By the time of his execution in 1896, Holmes had engaged in a lengthy criminal career that included insurance fraud, forgery, swindling, three to four bigamous illegal marriages, horse theft and murder.")

        }
    }


}

/**
 * personName --> The name of the individual
 * description --> The description of the individual
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FameEntry(personName : String, description : String){

        var showDialog by remember { mutableStateOf(false) }
        if (showDialog) {
            CustomDialog(onDismissRequest = { showDialog = false}, personName, description)
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
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }

}


@Composable
fun CustomDialog(onDismissRequest: () -> Unit, individualName : String, description: String) {
    AlertDialog(
        onDismissRequest = { onDismissRequest() },
        title = { Text(text = individualName) },
        text = {
            Column {
                Text(description)
                Image(painter = painterResource(id = R.drawable.test_image), contentDescription = "Your Image Description")
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

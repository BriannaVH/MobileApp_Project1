package com.example.bottombardemo.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
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
import androidx.compose.ui.unit.dp
import com.example.bottombardemo.R

@Composable
fun Fame() {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
//        Icon(
//            imageVector = Icons.Filled.Home,
//            contentDescription = "home",
//            tint = Color.Blue,
//            modifier = Modifier.size(150.dp)
//                .align(Alignment.Center)
//        )

        FameEntry("Person 1", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse massa nunc, eleifend efficitur turpis sed, finibus convallis erat. Vestibulum lacinia fringilla diam vitae volutpat. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Fusce nec efficitur velit, placerat luctus mi. Donec ultricies turpis in quam aliquet convallis. Aliquam egestas tortor nec euismod volutpat. Integer congue cursus dolor, non ornare felis luctus sit amet. Nunc et elit tempus, consequat nisi eu, facilisis augue. Phasellus in leo mi. Nunc non iaculis dui. Ut eu libero in velit porttitor ullamcorper quis sed ante. Pellentesque sodales justo vel ante varius, non consectetur quam feugiat. Morbi vitae dui dapibus, pharetra diam sed, feugiat neque. Cras pharetra urna diam, luctus vulputate dui interdum a. Curabitur ac orci et velit imperdiet finibus.")
    }
}

/**
 * personName --> The name of the individual
 * description --> The description of the individual
 */
@Composable
fun FameEntry(personName : String, description : String){
    Surface {
        var showDialog by remember { mutableStateOf(false) }

        if (showDialog) {
            CustomDialog(onDismissRequest = { showDialog = false}, personName, description)
        }

        Column {

            Row {
                Text(personName)
                Spacer(modifier = Modifier.width(10.dp))
                Button(onClick = { showDialog = true }) {
                    Text(text = "Learn More...")
                }
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
                Text("Cancel")
            }
        }
    )
}

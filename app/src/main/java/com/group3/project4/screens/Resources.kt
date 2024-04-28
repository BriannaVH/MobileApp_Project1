package com.group3.project4.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Resources() {

    Box(
        modifier = Modifier
            .wrapContentWidth()
            .verticalScroll(rememberScrollState())
            .background(color = Color.Red)
    ) {
        Column(
            modifier = Modifier
                .background(color = Color.Yellow)
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp)
            ,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text("UVM Campus Resouces", style = MaterialTheme.typography.titleLarge)
        }




    }
}


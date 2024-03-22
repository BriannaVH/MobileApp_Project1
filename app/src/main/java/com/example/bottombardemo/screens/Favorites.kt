package com.example.bottombardemo.screens

import android.graphics.drawable.BitmapDrawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bottombardemo.R

@Composable
fun Favorites() {

    //simple expandable card example with just text
    var lorem_ipsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi efficitur malesuada risus, eu pellentesque metus tincidunt sed. Praesent dapibus odio ut mollis porta. Nam imperdiet porttitor nisi, vel dapibus mauris laoreet eu. Donec mauris purus, hendrerit volutpat vestibulum a, convallis ac purus. Mauris ac eleifend justo. Pellentesque ac lectus a tortor commodo cursus sed sed risus. Aenean eu ligula suscipit nunc mollis rutrum. Mauris non scelerisque urna. Mauris et ultricies orci. Nullam turpis massa, aliquet et tortor at, venenatis vehicula ipsum. Sed placerat tellus purus, vitae elementum est pharetra vitae. Etiam et odio sapien. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Morbi ultrices ac felis a tincidunt. Duis non sem non diam ultricies finibus."
    var example_description = "Hello World"
    ExpandableTextCard(headerString = example_description, descriptionString = lorem_ipsum)
}

/**
 * Source:
 * https://medium.com/@acceldia/jetpack-compose-creating-expandable-cards-with-content-9ea1eae09efe
 *
 * Expandable card that only supports text
 */
@Composable
fun ExpandableTextCard(headerString: String, descriptionString: String){
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
            Text(
                text = headerString,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(8.dp)
            )

            if(isExpanded){
                Text(
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(8.dp),
                    text = descriptionString
                )
            }
        }
    }
}
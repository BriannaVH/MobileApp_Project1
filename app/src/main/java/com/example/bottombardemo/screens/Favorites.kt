package com.example.bottombardemo.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun Favorites() {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Icon(
            imageVector = Icons.Filled.Favorite,
            contentDescription = "favorites",
            tint = Color.Blue // ,
//            modifier = Modifier.size(150.dp)
//                .align(Alignment.Center)
        )

        Text("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque ipsum velit, dictum eget velit rutrum, tempus mollis orci. Fusce sollicitudin ullamcorper augue at pellentesque. Integer sit amet felis at est placerat malesuada nec eget tellus. In vitae risus tristique, volutpat velit sit amet, tristique magna. Cras a vestibulum ipsum. Donec commodo quis felis eu convallis. Nulla eget auctor neque. Aliquam ut dapibus ex. Vestibulum bibendum sapien vitae malesuada cursus. Ut efficitur gravida tortor non porta. Maecenas vulputate metus in magna facilisis, quis condimentum est tristique.")
        Text("In dignissim urna at consectetur pretium. Sed non odio mauris. Morbi eget egestas nunc. Aliquam erat volutpat. Etiam condimentum tincidunt dapibus. Aliquam sapien quam, mattis quis scelerisque at, tincidunt ut mauris. Vestibulum in rhoncus nulla, eu tempor massa. Vivamus rutrum, tortor sed ornare egestas, mi nulla pharetra velit, eget lobortis tortor purus et ipsum. Sed a facilisis orci. Pellentesque sed ligula risus. In fringilla lectus lorem, et sollicitudin dolor fringilla vel. Nam dictum ac velit et fringilla. Donec maximus cursus nisi scelerisque elementum. Integer convallis elit lectus. Aliquam in euismod urna. Nunc eleifend, lorem at euismod congue, eros nunc dapibus mauris, non hendrerit nunc lorem varius sem.")
        Text("Suspendisse potenti. Mauris et nibh leo. Donec pellentesque ligula orci, efficitur ultrices lectus faucibus non. Fusce sit amet tincidunt urna. Pellentesque iaculis dignissim neque, a rhoncus tortor vehicula vel. Etiam ultricies felis ac libero fermentum, eget rutrum felis cursus. Morbi ut quam in elit facilisis tincidunt. Interdum et malesuada fames ac ante ipsum primis in faucibus. Quisque tincidunt sit amet lorem vitae pretium. Fusce elementum rutrum sem, quis convallis nibh bibendum vel. Vestibulum non blandit nulla. Nulla id rutrum justo.")

    }
}
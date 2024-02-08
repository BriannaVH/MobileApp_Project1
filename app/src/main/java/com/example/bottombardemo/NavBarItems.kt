package com.example.bottombardemo


object NavBarItems {
    val BarItems = listOf(
        BarItem(
            title = "Acad",
            image = R.drawable.book,
            route = "academics"
        ),
        BarItem(
            title = "Sports",
            image = R.drawable.football,
            route = "sports"
        ),
        BarItem(
            title = "Safety",
            image = R.drawable.face,
            route = "home"
        ),
        BarItem(
            title = "Contact",
            image = R.drawable.phone,
            route = "contacts"
        ),
        BarItem(
//            title = "Favorites",
            title = "Favorites",
            image = R.drawable.heart_solid,
            route = "favorites"
        )
    )
}
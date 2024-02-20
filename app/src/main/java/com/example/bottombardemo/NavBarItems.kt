package com.example.bottombardemo


object NavBarItems {
    val BarItems = listOf(
        BarItem(
            title = "Acad",
            image = R.drawable.book,
            route = "academics"
        ),
        BarItem(
            title = "Trivial",
            image = R.drawable.questionmark,
            route = "trivial"
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
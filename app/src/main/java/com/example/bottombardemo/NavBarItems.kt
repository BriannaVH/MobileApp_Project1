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
            title = "Fame",
            image = R.drawable.medal,
            route = "fame"
        ),
        BarItem(
            title = "Schools",
            image = R.drawable.grad_cap,
            route = "Schools"
        ),
        BarItem(
//            title = "Favorites",
            title = "Favorites",
            image = R.drawable.heart_solid,
            route = "favorites"
        )
    )
}
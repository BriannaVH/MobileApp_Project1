package com.universityofvermont.UVMApp


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
            title = "Home",
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
            title = "Resources",
            image = R.drawable.tools,
            route = "resources"
        )
    )
}
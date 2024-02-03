package com.example.bottombardemo

sealed class NavRoutes(val route: String) {
//    object Sports1: NavRoutes("sports1")
    object Academics : NavRoutes("academics")
    object Sports : NavRoutes("sports")
    object Home : NavRoutes("home")
    object Contacts : NavRoutes("contacts")
    object Favorites : NavRoutes("favorites")

}

package com.example.bottombardemo

//TEST COMMENT IN NEW REPO
sealed class NavRoutes(val route: String) {
//    object Sports1: NavRoutes("sports1")
    object Academics : NavRoutes("academics")
    object Trivial : NavRoutes("trivial")
    object Home : NavRoutes("home")
    object Contacts : NavRoutes("contacts")
    object Favorites : NavRoutes("favorites")

}

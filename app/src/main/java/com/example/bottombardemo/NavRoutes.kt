package com.example.bottombardemo

//TEST COMMENT IN NEW REPO
sealed class NavRoutes(val route: String) {
//    object Sports1: NavRoutes("sports1")
    object Academics : NavRoutes("academics")
    object Trivial : NavRoutes("trivial")
    object Fame : NavRoutes("fame")
    object Schools : NavRoutes("Schools")
    object Favorites : NavRoutes("favorites")

}

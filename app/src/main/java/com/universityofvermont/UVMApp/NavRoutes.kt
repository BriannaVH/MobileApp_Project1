package com.universityofvermont.UVMApp

//TEST COMMENT IN NEW REPO
sealed class NavRoutes(val route: String) {
//    object Sports1: NavRoutes("sports1")
    object Academics : NavRoutes("academics")
    object Trivial : NavRoutes("trivial")
    object Fame : NavRoutes("fame")
    object Schools : NavRoutes("Schools")
    object Resources : NavRoutes("resources")

}

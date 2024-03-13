package com.example.bottombardemo



import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bottombardemo.screens.Academics
import com.example.bottombardemo.screens.Contacts
import com.example.bottombardemo.screens.Favorites
import com.example.bottombardemo.screens.Fame
import com.example.bottombardemo.screens.Trivial
import com.example.bottombardemo.ui.theme.BottomBarDemoTheme

class MainActivity : ComponentActivity() {
    //Test comment from bree
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BottomBarDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val owner = LocalViewModelStoreOwner.current

                    owner?.let {
                        val viewModel: MainViewModel = viewModel(
                            it,
                            "MainViewModel",
                            MainViewModelFactory(
                                LocalContext.current.applicationContext
                                        as Application)
                        )

                        MainScreen(viewModel)
                    }


                }
            }
        }
    }
}


class MainViewModelFactory(val application: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(application) as T
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: MainViewModel) {
    val navController = rememberNavController()

    Scaffold(
        topBar = { TopAppBar(title = {Text("UVM Student Portal", textAlign = TextAlign.Center)})  },
        content = { padding ->
            Column(Modifier.padding(padding)) {
                NavigationHost(navController = navController, viewModel = viewModel)
            } },
        bottomBar = { BottomNavigationBar(navController = navController)}
    )
}

@Composable
fun NavigationHost(navController: NavHostController, viewModel: MainViewModel) {

    NavHost(
        navController = navController,
        startDestination = NavRoutes.Academics.route,
    ) {
        composable(NavRoutes.Trivial.route) {
            Trivial(viewModel)
        }
        composable(NavRoutes.Academics.route) {
            val allCourses by viewModel.allCourses.observeAsState(listOf())
            val searchResults by viewModel.searchResults.observeAsState(listOf())
            Academics(
                allCourses = allCourses,
                searchResults = searchResults,
                viewModel = viewModel)
        }
        composable(NavRoutes.Home.route) {
            Fame()
        }

        composable(NavRoutes.Contacts.route) {
            Contacts()
        }

        composable(NavRoutes.Favorites.route) {
            Favorites()
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {

    NavigationBar {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        NavBarItems.BarItems.forEach { navItem ->

            NavigationBarItem(
                selected = currentRoute == navItem.route,
                onClick = {
                    navController.navigate(navItem.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },

                icon = {
                    Image(imageVector = ImageVector.vectorResource(navItem.image),
                        contentDescription = navItem.title)
                },
                label = {
                    Text(text = navItem.title)
                },
            )
        }


    }
}

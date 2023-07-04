package hr.sandro.chordiatocompose

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import hr.sandro.chordiatocompose.presentation.favourite.FavouritesScreen
import hr.sandro.chordiatocompose.presentation.history.HistoryScreen
import hr.sandro.chordiatocompose.presentation.main.SheetScreen
import hr.sandro.chordiatocompose.presentation.shared.BottomNavItem
import hr.sandro.chordiatocompose.presentation.ui.theme.ChordiatoComposeTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChordiatoComposeTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(
                            items = listOf(
                                BottomNavItem(
                                    name = "History",
                                    route = "history",
                                    icon = Icons.Default.Refresh
                                ),
                                BottomNavItem(
                                    name = "Sheet",
                                    route = "sheet",
                                    icon = Icons.Default.Home
                                ),
                                BottomNavItem(
                                    name = "Favourites",
                                    route = "favourites",
                                    icon = Icons.Default.Favorite
                                ),
                            ),
                            navController = navController,
                            onItemClick = {
                                navController.navigate(it.route)
                            }
                        )
                    }
                ) {
                    Navigation(navController = navController)
                }
            }
        }
    }
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "sheet?link={link}") {
        composable(
            route = "sheet?link={link}",
            arguments = listOf(navArgument("link") {
                type = NavType.StringType
                nullable = true
            })
        ) { backStackEntry ->
            Log.d("Args", backStackEntry.arguments?.getString("link").toString())
            SheetScreen(
                link = backStackEntry.arguments?.getString("link"),
                navController = navController
            )
        }
        composable("history") {
            HistoryScreen()
        }
        composable("favourites") {
            FavouritesScreen(navController = navController)
        }
    }

}

@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit,
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomNavigation(
        modifier = modifier,
        elevation = 4.dp
    ) {
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                onClick = { onItemClick(item) },
                icon = {
                    Icon(imageVector = item.icon, contentDescription = null)
                }
            )
        }
    }
}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ChordiatoComposeTheme {
        Greeting("Android")
    }
}
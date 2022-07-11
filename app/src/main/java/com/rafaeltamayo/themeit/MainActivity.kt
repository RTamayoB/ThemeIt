package com.rafaeltamayo.themeit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.animation.*
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.rafaeltamayo.themeit.presentation.ui.theme.ThemeItTheme
import com.rafaeltamayo.themeit.presentation.views.CreateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }

    ThemeItTheme {
        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        when (navBackStackEntry?.destination?.route) {
            "createTheme" -> {
                bottomBarState.value = false
            }
            "material2List" -> {
                bottomBarState.value = true
            }
            "material3List" -> {
                bottomBarState.value = true
            }
        }

        Scaffold(
            topBar = { TopAppBar {} },
            floatingActionButton = {
                AnimatedVisibility(
                    visible = bottomBarState.value,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    FloatingActionButton(
                        onClick = {
                            navController.navigate("createTheme")
                        }) {
                        Icon(imageVector = Icons.Filled.Add, contentDescription = "Create new theme")
                    }
                }
            },
            floatingActionButtonPosition = FabPosition.Center,
            isFloatingActionButtonDocked = true,
            bottomBar = {
                BottomBar(
                    navController = navController,
                    bottomBarState = bottomBarState
                )
            },
        ) { innerPadding ->

            NavHost(navController, startDestination = Screen.Material2List.route, Modifier.padding(innerPadding)) {
                composable("createTheme") { CreateTheme() }
                composable(Screen.Material2List.route) {
                    LaunchedEffect(Unit) {
                        bottomBarState.value = true
                    }
                    Material2List()
                }
                composable(Screen.Material3List.route) {
                    LaunchedEffect(Unit) {
                        bottomBarState.value = true
                    }
                    Material3List()
                }
            }
        }
    }
}

@Composable
fun BottomBar(navController: NavController, bottomBarState: MutableState<Boolean>) {
    AnimatedVisibility(
        visible = bottomBarState.value,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY =  { it })
    ) {
        BottomAppBar(
            cutoutShape = MaterialTheme.shapes.small.copy(
                CornerSize(percent = 50)
            )
        ) {
            BottomNav(navController = navController)
        }
    }
}

@Composable
fun BottomNav(navController: NavController) {
    val items = listOf(
        Screen.Material2List,
        Screen.Material3List,
    )

    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        items.forEach { screen ->
            BottomNavigationItem(
                icon = { Icon(screen.icon, contentDescription = null) },
                label = { Text(screen.tabName) },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun Material2List() {
    Text(text = "Material 2")
}

@Composable
fun Material3List() {
    Text(text = "Material 3")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ThemeItTheme {
        MainScreen()
    }
}

sealed class Screen(val route: String, val tabName: String, val icon: ImageVector) {
    object Material2List : Screen("material2List", "Material 2", Icons.Filled.Person)
    object Material3List : Screen("material3List", "Material 3", Icons.Filled.Phone)
}
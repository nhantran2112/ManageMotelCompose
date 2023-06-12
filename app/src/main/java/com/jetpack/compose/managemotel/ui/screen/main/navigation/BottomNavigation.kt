package com.jetpack.compose.managemotel.ui.screen.main.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.jetpack.compose.managemotel.R

val items = listOf(
    mapOf("icon" to R.drawable.ic_home, "title" to "Home"),
    mapOf("icon" to R.drawable.ic_bar_chart, "title" to "Statistic"),
    mapOf("icon" to R.drawable.ic_settings, "title" to "Settings"),
)

@Composable
fun MainBottomNavigation(navController: NavController, modifier: Modifier = Modifier) {
    var selectedItem by remember { mutableStateOf(0) }

    BottomNavigation(
        modifier = modifier, backgroundColor = MaterialTheme.colorScheme.background
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEachIndexed { index, item ->
            BottomNavigationItem(icon = {
                Icon(
                    painter = painterResource(id = item["icon"] as Int),
                    contentDescription = null,
                )
            }, label = {
                Text(text = item["title"] as String)
            }, selected = currentRoute == item["title"] as String,
                onClick = {
                    navController.navigate(item["title"] as String) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                    }
                })
        }
    }
}

sealed class Screen(val icon: Int, val title: String) {
    object Home : Screen(R.drawable.ic_home, "Home")
    object Statistic : Screen(R.drawable.ic_bar_chart, "Statistic")
    object Settings : Screen(R.drawable.ic_settings, "Settings")
}

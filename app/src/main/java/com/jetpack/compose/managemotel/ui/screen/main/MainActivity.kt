package com.jetpack.compose.managemotel.ui.screen.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jetpack.compose.managemotel.data.repository.remote.RemoteImpl
import com.jetpack.compose.managemotel.ui.screen.home.HomeScreen
import com.jetpack.compose.managemotel.ui.screen.main.navigation.MainBottomNavigation
import com.jetpack.compose.managemotel.ui.screen.main.navigation.Screen
import com.jetpack.compose.managemotel.ui.screen.setting.SettingScreen
import com.jetpack.compose.managemotel.ui.screen.statistic.StatisticScreen
import com.jetpack.compose.managemotel.ui.theme.ManageMotelTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
private fun MyApp() {
    val navController = rememberNavController()

    ManageMotelTheme {
        Scaffold(bottomBar = { MainBottomNavigation(navController) }) {
            NavHost(
                navController = navController,
                startDestination = Screen.Home.title,
                modifier = Modifier.padding(it)
            ) {
                composable(Screen.Home.title) { HomeScreen() }
                composable(Screen.Statistic.title) { StatisticScreen() }
                composable(Screen.Settings.title) { SettingScreen() }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApp()
}
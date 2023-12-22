package com.test.faurecia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.test.faurecia.feature.detail.AppDetailNavigation
import com.test.faurecia.feature.detail.AppDetailScreen
import com.test.faurecia.feature.list.AppListNavigation
import com.test.faurecia.feature.list.AppListScreen
import com.test.faurecia.ui.theme.FaureciaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FaureciaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController, startDestination = "appList") {
                        composable("appList") {
                            AppListNavigation(navController)
                        }
                        composable("appDetail/{appId}") { backStackEntry ->
                            val appId = backStackEntry.arguments?.getString("appId").orEmpty()
                            AppDetailNavigation(appId)
                        }
                    }
                }
            }
        }
    }
}


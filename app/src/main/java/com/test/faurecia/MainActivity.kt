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
import com.test.faurecia.Route.APPID_ARG
import com.test.faurecia.Route.DETAIL
import com.test.faurecia.Route.LIST
import com.test.faurecia.feature.detail.AppDetailNavigation
import com.test.faurecia.feature.list.AppListNavigation
import com.test.faurecia.ui.theme.FaureciaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FaureciaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController, startDestination = LIST) {
                        composable(LIST) {
                            AppListNavigation(navController = navController)
                        }
                        composable(DETAIL) { backStackEntry ->
                            val appId = backStackEntry.arguments?.getString(APPID_ARG).orEmpty()
                            AppDetailNavigation(
                                navController = navController,
                                appId = appId
                            )
                        }
                    }
                }
            }
        }
    }

}


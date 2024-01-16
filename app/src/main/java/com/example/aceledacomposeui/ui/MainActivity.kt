package com.example.aceledacomposeui.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.aceledacomposeui.data.PreferenceManager
import com.example.aceledacomposeui.ui.theme.AceledaComposeUITheme
import com.example.aceledacomposeui.utils.Constants.ThemeMode
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aceledacomposeui.ui.screen.AppScreen
import com.example.aceledacomposeui.ui.ui.HomeNewScreen
import com.example.aceledacomposeui.ui.ui.HomeScreenKt
import com.example.aceledacomposeui.ui.ui.NotificationKt
import com.example.aceledacomposeui.ui.ui.ProfileKt
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    companion object {
        fun start(mContext: Context) {
            mContext.startActivity(Intent(mContext, MainActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AceledaComposeUITheme(
                darkTheme = PreferenceManager.getBoolean(ThemeMode, this)
            ) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavDatabaseGraph(this)
                }
            }
        }
    }
}

@Composable
fun NavDatabaseGraph(mActivity: Activity) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreen.HomeScreen.route,
        /*enterTransition = { // push
            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(700))
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(700)
            )
        }*/
    ) {

        composable(route = AppScreen.HomeScreen.route) { navBackResult ->
            HomeNewScreen(navController, mActivity, navBackResult)
        }

        composable(route = AppScreen.SeeMoreScreen.route) {
            InitScreen(navController)
        }

        composable(route = AppScreen.ProfileScreen.route) {
            ProfileKt(navController, mActivity)
        }

        composable(route = AppScreen.HomeNewScreen.route) {
            HomeScreenKt(navController)
        }

        /*composable(route = AppScreen.NotificationScreen.route) {
             NotificationKt(navController = navController)
        }*/

        composable(
            route = AppScreen.NotificationScreen.route,
        ) {
            NotificationKt(navController = navController)
        }

        /* composable(
             route = AppScreen.UpdateScreen.route,
             arguments = listOf(navArgument("id") {
                 type = NavType.StringType
             })
         ){
             EditUserName(navController)
         }*/
    }
}



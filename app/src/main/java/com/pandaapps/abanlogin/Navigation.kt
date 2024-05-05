package com.pandaapps.abanlogin

import aban.com.AbanLogin.ui.theme.Login.LogInScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.pandaapps.abanlogin.SignUp.PrivacyScreen
import com.pandaapps.abanlogin.SignUp.SignUpScreen


sealed class Route {
    data class LogInScreen(val name: String = "Login") : Route()
    data class SignUpScreen(val name: String = "SignUp") : Route()
    data class PrivacyPolicyScreen(val name: String = "PrivacyPolicy") : Route()
    data class HomeScreen(val name: String = "Home") : Route()

}

@Composable

fun MyNavigation(navHostController: NavHostController) {

    NavHost(navController = navHostController, startDestination = "login_flow") {

        navigation(startDestination = Route.LogInScreen().name, route = "login_flow") {

            composable(route = Route.LogInScreen().name) {
                LogInScreen(
                    onLogInClick = {
                        navHostController.navigate(
                            Route.HomeScreen().name
                        ) {
                            popUpTo(route = "login_flow")
                        }
                    },
                    onSignUpClick = {
                        navHostController.navigateToSingleTop(
                            Route.SignUpScreen().name
                        )


                    }
                )
            }

            composable(route = Route.SignUpScreen().name) {
                SignUpScreen(
                    onSignUpClick = {
                        navHostController.navigate(
                            Route.HomeScreen().name
                        ){
                            popUpTo("login_flowp")
                        }
                    },
                    onLoginClick = {
                        navHostController.navigateToSingleTop(
                            Route.LogInScreen().name
                        )
                    },
                    onPrivacyPolicyClick = {
                        navHostController.navigate(
                            Route.PrivacyPolicyScreen().name
                        ){
                            launchSingleTop = true
                        }
                    }
                )
            }

            composable(route = Route.PrivacyPolicyScreen().name) {
                PrivacyScreen {
                    navHostController.navigateUp()
                }
            }

        }




        composable(route = Route.HomeScreen().name) {
            HomeScreen()
        }

    }


}

fun NavController.navigateToSingleTop(route: String) {
    navigate(route) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }

        launchSingleTop = true

        restoreState = true


    }
}
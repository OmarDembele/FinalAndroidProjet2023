package com.esmt.cours.finalandroidprojet2023.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.esmt.cours.finalandroidprojet2023.presentation.viewModel.AuthViewModel


enum class LoginRoute{
    SignUp,
    SignIn
}

enum class HomeRoutes{
    HomePage,
    Detail
}


@Composable
fun Navigation(
    navController: NavHostController = rememberNavController(),
    authViewModel: AuthViewModel
){
    NavHost(navController = navController,
        startDestination = LoginRoute.SignIn.name
    ){
        composable(route = LoginRoute.SignIn.name){
            LoginPage(
                onNavToHomePage = { navController.navigate(HomeRoutes.HomePage.name){
                    launchSingleTop = true
                    popUpTo(route = LoginRoute.SignIn.name){
                        inclusive = true
                        }
                    }
                },
                authViewModel = authViewModel
                ){
                navController.navigate(LoginRoute.SignUp.name){
                    launchSingleTop = true
                    popUpTo(LoginRoute.SignIn.name){
                        inclusive = true
                    }
                }
            }
        }
        composable(route = LoginRoute.SignUp.name){
            RegisterPage(
                onNavToHomePage = { navController.navigate(HomeRoutes.HomePage.name){
                    launchSingleTop = true
                    popUpTo(route = LoginRoute.SignUp.name){
                        inclusive = true
                        }
                    }
                },
                authViewModel = authViewModel
            ){
                navController.navigate(LoginRoute.SignIn.name)
            }
        }
        composable(route = HomeRoutes.HomePage.name){
            HomePage()
        }
    }
}
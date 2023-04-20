package com.esmt.cours.finalandroidprojet2023

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.esmt.cours.finalandroidprojet2023.presentation.*
import com.esmt.cours.finalandroidprojet2023.ui.theme.FinalAndroidProjet2023Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val authViewModel = viewModel(modelClass = AuthViewModel::class.java)
            FinalAndroidProjet2023Theme {
                Navigation(authViewModel = authViewModel)
            }
        }
    }
}

/*
@Composable
fun LoginApplication(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login_page", builder = {
        composable("login_page", content = { LoginPage(navController = navController, onNavToHomePage = ) })
        composable("register_page", content = { RegisterPage(navController = navController) })
        composable("home_page", content = { HomePage(navController = navController) })
    })
}*/

package com.esmt.cours.finalandroidprojet2023

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.esmt.cours.finalandroidprojet2023.presentation.LoginPage
import com.esmt.cours.finalandroidprojet2023.presentation.RegisterPage
import com.esmt.cours.finalandroidprojet2023.ui.theme.FinalAndroidProjet2023Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FinalAndroidProjet2023Theme {
                LoginApplication()
            }
        }
    }
}


@Composable
fun LoginApplication(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login_page", builder = {
        composable("login_page", content = { LoginPage(navController = navController) })
        composable("register_page", content = { RegisterPage(navController = navController) })
    })
}

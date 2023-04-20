@file:Suppress("DEPRECATION")

package com.esmt.cours.finalandroidprojet2023.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.esmt.cours.finalandroidprojet2023.R
import com.esmt.cours.finalandroidprojet2023.ui.theme.FinalAndroidProjet2023Theme
import com.esmt.cours.finalandroidprojet2023.ui.theme.primaryColor



@Composable
fun LoginPage(
    authViewModel: AuthViewModel? = null,
    onNavToHomePage:() ->Unit,
    onNavToSignUpPage:() ->Unit,
    //navController: NavController?
) {

    val loginUiState = authViewModel?.loginUiState
    val isError = loginUiState?.loginError != null
    val context = LocalContext.current

    val passwordVisibility = remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White), contentAlignment = Alignment.TopCenter
        ) {
            Image(painter = painterResource(id = R.drawable.logo_auth), contentDescription = "image authentification")
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.60f)
                .clip(RoundedCornerShape(30.dp))
                .background(Color.White)
                .padding(10.dp)
        ) {

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Sign In",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp
                    ),
                    fontSize = 30.sp
                )

                if (isError){
                    Text(text = loginUiState?.loginError ?: "Unknown error",
                    color = Color.Red)
                }
                Spacer(modifier = Modifier.padding(20.dp))
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    OutlinedTextField(
                        value = loginUiState?.userName ?: "",
                        onValueChange = { authViewModel?.onUserNameChange(it) },
                        label = { Text(text = "Email Address") },
                        placeholder = { Text(text = "Email Address") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(0.8f),
                        isError = isError
                    )

                    OutlinedTextField(
                        value = loginUiState?.password ?: "",
                        onValueChange = { authViewModel?.onPasswordNameChange(it) },
                        trailingIcon = {
                            IconButton(onClick = {
                                passwordVisibility.value = !passwordVisibility.value
                            }) {
                                Icon(painter = painterResource( R.drawable.password_eye) ,
                                    contentDescription = "",
                                    tint = if (passwordVisibility.value) primaryColor else Color.Gray)
                            }
                        },
                        label = { Text("Password") },
                        placeholder = { Text(text = "Password") },
                        singleLine = true,
                        visualTransformation = if (passwordVisibility.value) VisualTransformation.None
                        else PasswordVisualTransformation(),
                        isError = isError,
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .focusRequester(focusRequester = focusRequester),
                    )

                    Spacer(modifier = Modifier.padding(10.dp))
                    Button(
                        onClick = {authViewModel?.loginUser(context)},
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .height(50.dp)
                    ) {
                        Text(text = "Sign In", fontSize = 20.sp)
                    }

                    Spacer(modifier = Modifier.padding(20.dp))
                    TextButton(onClick = { onNavToSignUpPage.invoke() }) {
                        Text(text = "Create An Account")
                    }
                    /*Text(
                        text = "Create An Account",
                        modifier = Modifier.clickable(onClick = {
                            navController?.navigate("register_page"){
                                popUpTo = navController.graph.startDestinationId
                                launchSingleTop = true
                            }
                        })
                    )*/
                    Spacer(modifier = Modifier.padding(20.dp))
                }
            }
        }


        if(loginUiState?.isLoading == true){
            CircularProgressIndicator()
        }

        LaunchedEffect(key1 = authViewModel?.hasUser){
            if(authViewModel?.hasUser == true){
                onNavToHomePage.invoke()
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun Login_page_view(){
    FinalAndroidProjet2023Theme() {
        LoginPage( onNavToHomePage = { /*TODO*/ }){

        }
    }
}




















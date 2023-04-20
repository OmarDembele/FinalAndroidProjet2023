package com.esmt.cours.finalandroidprojet2023.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.esmt.cours.finalandroidprojet2023.ui.theme.whiteBackground
import androidx.compose.foundation.layout.Spacer as Spacer1


@Suppress("DEPRECATION")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RegisterPage(
    authViewModel: AuthViewModel? = null,
    onNavToHomePage:() ->Unit,
    onNavToLoginPage:() ->Unit,
    //navController: NavController?
) {

    val loginUiState = authViewModel?.loginUiState
    val isError = loginUiState?.signUpError != null
    val context = LocalContext.current

    val passwordVisibility = remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            contentAlignment = Alignment.TopCenter
        ) {
            Image(painter = painterResource(id = R.drawable.register_page), contentDescription = "")
        }


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.70f)
                .clip(RoundedCornerShape(30.dp))
                .background(whiteBackground)
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Sign Up", fontSize = 30.sp,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp
                    )
                )
                if (isError){
                    Text(text = loginUiState?.signUpError ?: "Unknown error",
                        color = Color.Red)
                }
                Spacer1(modifier = Modifier.padding(20.dp))
                Column(horizontalAlignment = Alignment.CenterHorizontally) {

                    OutlinedTextField(
                        value = loginUiState?.userNameSignUp ?:"",
                        onValueChange = { authViewModel?.onUserNameChangeSignUp(it) },
                        label = { Text(text = "Email") },
                        placeholder = { Text(text = "Email") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(0.8f)
                    )
                    Spacer1(modifier = Modifier.padding(2.dp))

                    OutlinedTextField(
                        value = loginUiState?.passwordSignUp ?:"",
                        onValueChange = { authViewModel?.onPasswordNameChangeSignUp(it)  },
                        label = { Text(text = "Password") },
                        placeholder = { Text(text = "Password") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(0.8f),
                        trailingIcon = {
                            IconButton(onClick = {
                                passwordVisibility.value = !passwordVisibility.value
                            }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.password_eye),
                                    contentDescription = "",
                                    tint = if (passwordVisibility.value) primaryColor else Color.Gray
                                )
                            }
                        },
                        visualTransformation = if (passwordVisibility.value) VisualTransformation.None
                        else PasswordVisualTransformation()
                    )

                    OutlinedTextField(
                        value = loginUiState?.confirmPasswordSignUp ?:"",
                        onValueChange = { authViewModel?.onConfirmPasswordNameChangeSignUp(it)  },
                        label = { Text(text = "Confirmation Password") },
                        placeholder = { Text(text = "Confirmation Password") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(0.8f),
                        trailingIcon = {
                            IconButton(onClick = {
                                passwordVisibility.value = !passwordVisibility.value
                            }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.password_eye),
                                    contentDescription = "",
                                    tint = if (passwordVisibility.value) primaryColor else Color.Gray
                                )
                            }
                        },
                        visualTransformation = if (passwordVisibility.value) VisualTransformation.None
                        else PasswordVisualTransformation()
                    )


                    Spacer1(modifier = Modifier.padding(10.dp))
                    Button(onClick = { authViewModel?.createUser(context)}, modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(50.dp)) {
                        Text(text = "Sign Up", fontSize = 20.sp)
                    }
                    Spacer1(modifier = Modifier.padding(20.dp))
                    TextButton(onClick = { onNavToLoginPage.invoke() }) {
                        Text(text = "Login Instead")
                    }
                    /*Text(
                        text = "Login Instead",
                        modifier = Modifier.clickable(onClick = {
                            navController?.navigate("login_page"){
                                popUpTo = navController.graph.startDestinationId
                                launchSingleTop = true
                            }
                        })
                    )*/
                    Spacer1(modifier = Modifier.padding(20.dp))

                }

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Register_page_view(){
    FinalAndroidProjet2023Theme() {
        RegisterPage( onNavToHomePage = { /*TODO*/ }){

        }
    }
}



















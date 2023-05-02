package com.esmt.cours.finalandroidprojet2023.presentation.components.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.esmt.cours.finalandroidprojet2023.R


@Composable
fun HomeScreen(){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 10.dp, top = 70.dp, end = 10.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Bienvenue dans notre portail de gestion de Stock",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 30.sp,

            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 20.dp, end = 5.dp),
        )
        Image(painter = painterResource(id = R.drawable.logo_auth), contentDescription = "logo home")

    }
}
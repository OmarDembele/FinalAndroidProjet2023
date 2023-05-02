package com.esmt.cours.finalandroidprojet2023.presentation.components.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.esmt.cours.finalandroidprojet2023.domain.use_case.ProductEvent
import com.esmt.cours.finalandroidprojet2023.presentation.viewModel.ProductViewModel


@Composable
fun AddScreen(
    productViewModel: ProductViewModel = hiltViewModel(),
){
    val state by productViewModel.state.collectAsState()

    Column(modifier = Modifier
        .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Ajout d'un produit", style = TextStyle(textAlign = TextAlign.Center), fontWeight = FontWeight.Bold, fontSize = 25.sp)
        OutlinedTextField(
            value = state.name,
            onValueChange = { name ->
                productViewModel.onEvent(ProductEvent.ModifyName(name)) },
            label = { Text("Nom du produit") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        OutlinedTextField(
            value = state.price.toString(),
            onValueChange = { price ->
                productViewModel.onEvent(ProductEvent.ModifyPrice(price.toDouble())) },
            label = { Text("Prix du produit") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        OutlinedTextField(
            value = state.quantity.toString(),
            onValueChange = { quantity ->
                productViewModel.onEvent(ProductEvent.ModifyQuantity(quantity.toInt())) },
            label = { Text("Quantité du produit") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )
        OutlinedTextField(
            value = state.quantitySeuil.toString(),
            onValueChange = { quantitySeuil ->
                productViewModel.onEvent(ProductEvent.ModifyQuantitySeuil(quantitySeuil.toInt())) },
            label = { Text("Seuil de quantité") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )
        Button(
            onClick = { productViewModel.onEvent(ProductEvent.SaveProduct) },
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
        ) {
            Text("Enregistrer")
        }
    }
}

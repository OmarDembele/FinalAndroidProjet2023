package com.esmt.cours.finalandroidprojet2023.presentation.components.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.esmt.cours.finalandroidprojet2023.domain.entities.Product
import com.esmt.cours.finalandroidprojet2023.domain.use_case.ProductEvent
import com.esmt.cours.finalandroidprojet2023.presentation.viewModel.ProductViewModel
import kotlinx.coroutines.flow.map


@SuppressLint("FlowOperatorInvokedInComposition")
@Composable
fun DetailsScreen(
    productViewModel: ProductViewModel = hiltViewModel(),
) {

    val productListState = productViewModel.state.map { it.products }
        .collectAsState(initial = emptyList())
    DetailsProductList(productListState.value, onProductClicked = {})
}

@Composable
fun DetailsProductList(
    productList: List<Product>,
    onProductClicked: (Product) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 2.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "La liste des Produits en dessous du Seuil",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Red
            )
        }

        LazyColumn {
            items(productList.filter { it.quantity <= it.quantitySeuil }) { product ->
                DetailsProductListItem(product, onProductClicked)
            }
        }
    }
}


@Composable
fun DetailsProductListItem(
    product: Product,
    onProductClicked: (Product) -> Unit,
) {
    val productViewModel: ProductViewModel = hiltViewModel()
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = { onProductClicked(product) })
    ) {
        Column(modifier = Modifier
            .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = product.name, fontWeight = FontWeight.Bold, fontSize = 28.sp, textAlign = TextAlign.Center)
            Text(text = "Prix: ${product.price}", textAlign = TextAlign.Center)
            Text(text = "Quantité: ${product.quantity}", textAlign = TextAlign.Center)
            Text(text = "Seuil de quantité: ${product.quantitySeuil}", textAlign = TextAlign.Center)
        }
    }
}

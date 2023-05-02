package com.esmt.cours.finalandroidprojet2023.presentation.components.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.esmt.cours.finalandroidprojet2023.domain.entities.Product
import com.esmt.cours.finalandroidprojet2023.domain.use_case.ProductEvent
import com.esmt.cours.finalandroidprojet2023.presentation.viewModel.ProductViewModel
import kotlinx.coroutines.flow.map


@SuppressLint("StateFlowValueCalledInComposition", "FlowOperatorInvokedInComposition")
@Composable
fun PurchaseScreen(
    productViewModel: ProductViewModel = hiltViewModel(),
) {

    val productListState = productViewModel.state.map { it.products }
        .collectAsState(initial = emptyList())
    ProductPurchaseList(productListState.value, onProductClicked = {})
}


@Composable
fun ProductPurchaseList(
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
                text = "Achat de produit",
                style = MaterialTheme.typography.h4
            )
        }

        LazyColumn {
            items(productList) { product ->
                ProductPurchaseListItem(product)
            }
        }
    }
}


@Composable
fun ProductPurchaseListItem(
    product: Product,
) {
    val productViewModel: ProductViewModel = hiltViewModel()
    var quantity by remember { mutableStateOf(1) }
    val maxQuantity = product.quantity

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
    ) {
        Column(modifier = Modifier
            .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = product.name, fontWeight = FontWeight.Bold, fontSize = 28.sp, textAlign = TextAlign.Center)
            Text(text = "Prix: ${product.price}", textAlign = TextAlign.Center)
            Text(text = "Quantité disponible: ${product.quantity}", textAlign = TextAlign.Center)
            Text(
                text = "Quantité à acheter: ",
                modifier = Modifier.padding(end = 8.dp)
            )
            Row() {
                Button(
                    onClick = { if (quantity > 1) quantity-- },
                    modifier = Modifier
                        .padding(end = 2.dp),
                    colors = ButtonDefaults.textButtonColors()
                ) {
                    Text("-", fontSize = 25.sp, fontWeight = FontWeight.Bold)
                }
                Text(
                    text = "$quantity",
                    modifier = Modifier
                        .padding(start = 8.dp, top = 10.dp, end = 8.dp),
                    fontSize = 19.sp
                )
                Button(
                    onClick = { if (quantity < maxQuantity) quantity++ },
                    modifier = Modifier
                        .padding(start = 2.dp),
                        colors = ButtonDefaults.textButtonColors()
                ) {
                    Text("+", fontSize = 25.sp, fontWeight = FontWeight.Bold)
                }
            }

            Button(
                onClick = {
                    if (quantity > 0) {
                        product.id?.let {
                            ProductEvent.UpdateProductQuantity(
                                it, quantity)
                        }?.let { productViewModel.onEvent(it) }
                    }
                },
                enabled = quantity > 0,
            ) {
                Text(text = "Sell")
            }
        }
    }
}

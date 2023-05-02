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
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
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


@SuppressLint("StateFlowValueCalledInComposition", "FlowOperatorInvokedInComposition")
@Composable
fun ListScreen(
    productViewModel: ProductViewModel = hiltViewModel(),
) {

    val productListState = productViewModel.state.map { it.products }
        .collectAsState(initial = emptyList())
    ProductList(productListState.value, onProductClicked = {})
}


@Composable
fun ProductList(
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
            verticalAlignment = CenterVertically
        ) {
            Text(
                text = "La liste des Produits",
                style = MaterialTheme.typography.h4
            )
        }

        LazyColumn {
            items(productList) { product ->
                ProductListItem(product, onProductClicked)
            }
        }
    }
}


@Composable
fun ProductListItem(
    product: Product,
    onProductClicked: (Product) -> Unit,
) {
    val productViewModel: ProductViewModel = hiltViewModel()
    var editDialogOpen by remember { mutableStateOf(false) }

    var quantity by remember { mutableStateOf(1) }

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
            Row {
                IconButton(onClick = { editDialogOpen = true }) {
                    Icon(Icons.Default.Edit,
                        contentDescription = "Edit Product",
                        modifier = Modifier
                            .background(Color.Green)
                            .width(34.dp)
                            .height(40.dp)

                    )
                }
                IconButton(onClick = { productViewModel.onEvent(ProductEvent.DeleteProduct(product)) }) {
                    Icon(Icons.Default.Delete,
                        contentDescription = "Delete Product",
                        modifier = Modifier
                            .background(Color.Red)
                            .width(35.dp)
                            .height(39.dp)
                    )
                }
            }
        }
    }


    if (editDialogOpen) {
        AlertDialog(
            onDismissRequest = { editDialogOpen = false },
            title = { Text("Modifier la quantité") },
            text = {
                Row(
                    modifier = Modifier,
                        //.padding(top = 50.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = CenterVertically
                ) {
                    Button(
                        onClick = { if (quantity > 1) quantity-- },
                        modifier = Modifier
                            .padding(top = 15.dp, end = 2.dp)
                            .height(50.dp),
                        colors = ButtonDefaults.textButtonColors()
                    ) {
                        Text("-", fontSize = 25.sp, fontWeight = FontWeight.ExtraBold)
                    }
                    Text(
                        text = "$quantity",
                        modifier = Modifier
                            .padding(start = 8.dp, top = 10.dp, end = 8.dp),
                        fontSize = 19.sp
                    )
                    Button(
                        onClick = {  quantity++ },
                        modifier = Modifier
                            .padding(start = 2.dp, top = 15.dp)
                            .height(50.dp),
                        colors = ButtonDefaults.textButtonColors()
                    ) {
                        Text("+", fontSize = 25.sp, fontWeight = FontWeight.Bold)
                    }
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        if (quantity > 0) {
                            product.id?.let {
                                ProductEvent.UpdateProductByQuantity(
                                    it, quantity)
                            }?.let { productViewModel.onEvent(it) }
                        }
                        editDialogOpen = false
                    },
                    enabled = quantity > 0,

                ) {
                    Text("Modifier", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { editDialogOpen = false },
                ) {
                    Text("Annuler", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }
            }
        )
    }

}

package com.esmt.cours.finalandroidprojet2023.presentation.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import com.esmt.cours.finalandroidprojet2023.R
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.esmt.cours.finalandroidprojet2023.domain.use_case.NavigationItem
import com.esmt.cours.finalandroidprojet2023.domain.use_case.ProductEvent
import com.esmt.cours.finalandroidprojet2023.presentation.LoginRoute
import com.esmt.cours.finalandroidprojet2023.presentation.viewModel.ProductViewModel
import com.esmt.cours.finalandroidprojet2023.ui.theme.FinalAndroidProjet2023Theme
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch




@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomePage(){
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBar(scope = scope, scaffoldState = scaffoldState) },
        drawerContent = {
            Drawer(scope = scope, scaffoldState = scaffoldState, navController = navController)
        }
    ) {
        NavigationHome(navController = navController)
    }

}

@Composable
fun TopBar(scope: CoroutineScope, scaffoldState: ScaffoldState, navController: NavHostController = rememberNavController()){
    //val authViewModel: AuthViewModel
    TopAppBar(
        title = { Text(text = "Gestion de Stock", fontSize = 18.sp) },
        navigationIcon = {
            IconButton(onClick = {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Barre de menu")
            }
        },
        backgroundColor = Color.Cyan,
        contentColor = Color.Black,
        actions = {

            IconButton(onClick = {
                scope.launch {
                    Firebase.auth.signOut()//
                    navController.navigate(LoginRoute.SignIn.name) {
                        //launchSingleTop = true
                        navController.graph.startDestinationId.let {
                            popUpTo(it) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true

                    }
                }
            })
            {
                Image(painter = painterResource(id = R.drawable.ic_logout), contentDescription = "to login page")
            }
        }
    )

}

@Composable
fun Drawer(scope: CoroutineScope, scaffoldState: ScaffoldState, navController: NavController){

    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Add,
        NavigationItem.ListProduct,
        NavigationItem.Details,
        //Deconnection
    )

    Column(
        modifier = Modifier
            .background(color = Color.White)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(Color.Cyan),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
        Image(painter = painterResource(id = R.drawable.logo_auth),
            contentDescription = "Image authentification",
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth()
                .padding(10.dp))
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.dp)
        )

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { items ->
            DrawerItem(item = items, selected = currentRoute == items.route, onItemClick = {
                navController.navigate(items.route){
                    navController.graph.startDestinationRoute?.let { route ->
                        popUpTo(route){
                            saveState = true
                        }
                    }
                    launchSingleTop = true
                    restoreState = true
                }
                scope.launch {
                    scaffoldState.drawerState.close()
                }

            })
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "Etudiant LPTI3 DAR ESMT",
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(12.dp)
                .align(Alignment.CenterHorizontally)
        )

    }
}

@Composable
fun DrawerItem(item: NavigationItem, selected: Boolean, onItemClick: (NavigationItem) -> Unit){
    val background = if(selected) R.color.grey else android.R.color.transparent
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(item) }
            .height(45.dp)
            .background(colorResource(id = background))
            .padding(start = 10.dp)
    ) {
        Image(painter = painterResource(id = item.icon), contentDescription = "Image icon",
            colorFilter = ColorFilter.tint(Color.Black),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .height(24.dp)
                .width(24.dp))

        Spacer(modifier = Modifier.width(7.dp))
        Text(
            text = item.title,
            fontSize = 16.sp,
            color = Color.Black
        )

    }

}

@Composable
fun HomeScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 10.dp, top = 90.dp, end = 10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Bienvenue dans notre portail de gestion de Stock",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 30.sp,

            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 5.dp, end = 5.dp),
        )
        Image(painter = painterResource(id = R.drawable.logo_auth), contentDescription = "logo home")

    }
}


@Composable
fun AddScreen(
    productViewModel: ProductViewModel = viewModel()
){

    ///val productViewModel = ViewModelProvider().get(ProductViewModel::class.java)
    //val productViewModel: ProductViewModel = viewModel()
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
                label = { Text("Nom du produit") }
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


@Composable
fun ListScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Settings Screen",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 30.sp,

            textAlign = TextAlign.Center,
        )

    }
}


@Composable
fun DetailsScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Share Screen",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 30.sp,
            textAlign = TextAlign.Center
        )

    }
}

/*@Composable
fun DeconnectionScreen(navController: NavController?){
    /*navController?.navigate("login_page"){
        popUpTo = navController.graph.startDestinationId
        launchSingleTop = true
    }*/
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Contact Screen",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 30.sp,
            textAlign = TextAlign.Center
        )
    }
}*/

@Composable
fun NavigationHome(navController: NavHostController = rememberNavController()){

    NavHost(navController, startDestination = NavigationItem.Home.route){

        composable(NavigationItem.Home.route){
            HomeScreen()
        }

        composable(NavigationItem.Add.route){
            AddScreen()
        }

        composable(NavigationItem.ListProduct.route){
            ListScreen()
        }

        composable(NavigationItem.Details.route){
            DetailsScreen()
        }

        /*composable(Deconnection.route){
            DeconnectionScreen(null)
        }*/

    }
}


@Preview(showBackground = true)
@Composable
fun Home_page_view(){
    FinalAndroidProjet2023Theme {
        HomePage()
    }
}

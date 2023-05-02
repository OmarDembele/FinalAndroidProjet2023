package com.esmt.cours.finalandroidprojet2023.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import com.esmt.cours.finalandroidprojet2023.R
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.esmt.cours.finalandroidprojet2023.domain.use_case.NavigationItem
import com.esmt.cours.finalandroidprojet2023.presentation.*
import com.esmt.cours.finalandroidprojet2023.ui.theme.FinalAndroidProjet2023Theme
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
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
fun TopBar(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
){
    val navController = rememberNavController()

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
                try {
                    scope.launch {
                        Firebase.auth.signOut()
                        navController.navigate(LoginRoute.SignIn.name)
                    }
                }catch(e: NullPointerException ){
                    e.printStackTrace()
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
        NavigationItem.Achat,
        NavigationItem.ListProduct,
        NavigationItem.Details,

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
            }
            )
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
fun NavigationHome(navController: NavHostController = rememberNavController()){

    NavHost(navController, startDestination = NavigationItem.Home.route){

        composable(NavigationItem.Home.route){
            HomeScreen()
        }

        composable(NavigationItem.Add.route){
            AddScreen()
        }

        composable(NavigationItem.Achat.route){
            PurchaseScreen()
        }

        composable(NavigationItem.ListProduct.route){
            ListScreen()
        }

        composable(NavigationItem.Details.route){
            DetailsScreen()
        }



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
        fun TopBar(
            scope: CoroutineScope,
            scaffoldState: ScaffoldState,
        ){
            val navController = rememberNavController()

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
                        try {
                            scope.launch {
                                Firebase.auth.signOut()
                                navController.navigate(LoginRoute.SignIn.name)
                            }
                        }catch(e: NullPointerException ){
                            e.printStackTrace()
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
                NavigationItem.Achat,
                NavigationItem.ListProduct,
                NavigationItem.Details,

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
                    }
                    )
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
        fun NavigationHome(navController: NavHostController = rememberNavController()){

            NavHost(navController, startDestination = NavigationItem.Home.route){

                composable(NavigationItem.Home.route){
                    HomeScreen()
                }

                composable(NavigationItem.Add.route){
                    AddScreen()
                }

                composable(NavigationItem.Achat.route){
                    PurchaseScreen()
                }

                composable(NavigationItem.ListProduct.route){
                    ListScreen()
                }

                composable(NavigationItem.Details.route){
                    DetailsScreen()
                }

            }
        }


        @Preview(showBackground = true)
        @Composable
        fun Home_page_view(){
            FinalAndroidProjet2023Theme {
                HomePage()
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun Home_page_view(){
    FinalAndroidProjet2023Theme {
        HomePage()
    }
}

package com.esmt.cours.finalandroidprojet2023.presentation

import com.esmt.cours.finalandroidprojet2023.R

sealed class NavigationItem(var route: String, var icon: Int, var title: String)
{
    object Home : NavigationItem("home", R.drawable.ic_home, "Home")
    object Profile : NavigationItem("Add", R.drawable.baseline_add_circle_24, "Add")
    object Settings : NavigationItem("Products", R.drawable.baseline_list_24, "Liste des produits")
    object Share : NavigationItem("Details", R.drawable.ic_details, "Details")
    object Contact : NavigationItem("Deconnexion", R.drawable.ic_logout, "deconnexion")
}

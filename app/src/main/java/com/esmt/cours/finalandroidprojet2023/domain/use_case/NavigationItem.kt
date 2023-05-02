package com.esmt.cours.finalandroidprojet2023.domain.use_case

import com.esmt.cours.finalandroidprojet2023.R

sealed class NavigationItem(var route: String, var icon: Int, var title: String)
{
    object Home : NavigationItem("home", R.drawable.ic_home, "Home")

    object Add : NavigationItem("Add", R.drawable.baseline_add_circle_24, "Add")

    object Achat : NavigationItem("Achat", R.drawable.ic_details, "achat")

    object ListProduct : NavigationItem("Products", R.drawable.baseline_list_24, "Liste des produits")

    object Details : NavigationItem("Details", R.drawable.ic_details, "Details")

}

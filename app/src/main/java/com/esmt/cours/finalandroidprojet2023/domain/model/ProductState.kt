package com.esmt.cours.finalandroidprojet2023.domain.model

import com.esmt.cours.finalandroidprojet2023.domain.entities.Product
import com.esmt.cours.finalandroidprojet2023.domain.entities.SortType

data class ProductState(
    val products: List<Product> = emptyList(),
    val name: String= "",
    val price: Double =0.0,
    val quantity: Int = 0,
    val quantitySeuil: Int = 0,
    val isAddingProduct: Boolean = false,
    val sortType: SortType = SortType.NAME
)

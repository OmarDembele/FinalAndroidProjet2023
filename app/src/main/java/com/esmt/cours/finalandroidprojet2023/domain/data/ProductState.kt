package com.esmt.cours.finalandroidprojet2023.domain.data

import com.esmt.cours.finalandroidprojet2023.domain.entities.Product

data class ProductState(
    val products: List<Product> = emptyList(),
    val name: String= "",
    val price: Long =0,
    val quantity: Int = 0,
    val quantitySeuil: Int = 0,
    val isAddingProduct: Boolean = false,
)

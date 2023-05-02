package com.esmt.cours.finalandroidprojet2023.domain.use_case

import com.esmt.cours.finalandroidprojet2023.domain.entities.Product
import com.esmt.cours.finalandroidprojet2023.domain.entities.SortType


sealed interface ProductEvent {

    object SaveProduct: ProductEvent
    data class DeleteProduct(val product: Product): ProductEvent
    object GetProduct: ProductEvent
    data class ModifyName(val name: String): ProductEvent
    data class ModifyPrice(val price: Double): ProductEvent
    data class ModifyQuantity(val quantity: Int): ProductEvent
    data class ModifyQuantitySeuil(val quantitySeuil: Int): ProductEvent
    data class SortProducts(val sortType: SortType): ProductEvent
    data class UpdateProductQuantity(val id: Int, val quantity: Int): ProductEvent
    data class UpdateProductByQuantity(val id: Int, val quantity: Int): ProductEvent

}
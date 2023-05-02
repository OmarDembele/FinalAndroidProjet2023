package com.esmt.cours.finalandroidprojet2023.domain.use_case

import com.esmt.cours.finalandroidprojet2023.data.repository.ProductRepository
import com.esmt.cours.finalandroidprojet2023.domain.entities.InvalidProductException
import com.esmt.cours.finalandroidprojet2023.domain.entities.Product

class AddProduct (
    private val repository: ProductRepository
) {

    @Throws(InvalidProductException::class)
    suspend operator fun invoke(product: Product) {
        if (product.name.isBlank()) {
            throw InvalidProductException("The name of the product cannot be empty.")
        }
        if (product.price <0) {
            throw InvalidProductException("The price of the product can't be null.")
        }
        if (product.quantity <0) {
            throw InvalidProductException("The quantity of the product can't be null.")
        }
        if (product.quantitySeuil <0) {
            throw InvalidProductException("The quantitySeuil of the product can't be null.")
        }
        repository.upsert(product = product)
    }
}
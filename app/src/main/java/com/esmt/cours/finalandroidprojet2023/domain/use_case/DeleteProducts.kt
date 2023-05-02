package com.esmt.cours.finalandroidprojet2023.domain.use_case

import com.esmt.cours.finalandroidprojet2023.data.repository.ProductRepository
import com.esmt.cours.finalandroidprojet2023.domain.entities.Product

class DeleteProducts (
    private val repository: ProductRepository
) {
    suspend operator fun invoke(product: Product) {
        repository.delete(product = product)
    }
}
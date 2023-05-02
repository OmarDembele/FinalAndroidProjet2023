package com.esmt.cours.finalandroidprojet2023.domain.use_case

import com.esmt.cours.finalandroidprojet2023.data.repository.ProductRepository

class UpdateProductByQuantity (
    private val repository: ProductRepository
    ) {
    suspend operator fun invoke(id: Int, newQuantity: Int) {
        repository.updateProductByQuantity(id, newQuantity)
    }
}
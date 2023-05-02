package com.esmt.cours.finalandroidprojet2023.data.repository


import com.esmt.cours.finalandroidprojet2023.domain.entities.Product
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun upsert(product: Product)

    suspend fun delete(product: Product)

    fun getProduct(): Flow<List<Product>>

    fun getProductsOrderedByName(): Flow<List<Product>>

    suspend fun getProductById(id: Int): Product?

    suspend fun updateProductQuantity(id: Int, quantity: Int): Job

    suspend fun updateProductByQuantity(id: Int, quantity: Int): Job

}
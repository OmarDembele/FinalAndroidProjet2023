package com.esmt.cours.finalandroidprojet2023.data.dao

import androidx.room.*
import com.esmt.cours.finalandroidprojet2023.domain.entities.ProductSold
import kotlinx.coroutines.flow.Flow


@Dao
interface  ProductSoldDao {
    @Upsert
    suspend fun upsertSold(productSold: ProductSold): Long

    @Delete
    suspend fun delete(product: ProductSold)

    @Query("SELECT * FROM productSold ")
    fun getProductSold(): Flow<List<ProductSold>>

    @Query("SELECT * FROM productSold WHERE id = :id")
    suspend fun getProductSoldById(id: Int): ProductSold?
}
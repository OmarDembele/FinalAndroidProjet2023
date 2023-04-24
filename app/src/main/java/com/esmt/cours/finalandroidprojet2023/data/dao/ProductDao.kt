package com.esmt.cours.finalandroidprojet2023.data.dao

import androidx.room.*
import com.esmt.cours.finalandroidprojet2023.domain.entities.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Upsert
    suspend fun upsert(product: Product)

    @Delete
    suspend fun delete(product: Product)

    @Query("SELECT * FROM product ")
    fun getProduct(): Flow<List<Product>>

    @Query("SELECT * FROM product ORDER BY name ASC")
    fun getProductsOrderedByName(): Flow<List<Product>>


    @Query("SELECT * FROM product WHERE id = :id")
    suspend fun getProductById(id: Int): Product

    //@Query("SELECT quantity as value FROM product WHERE id = :id")
    //suspend fun getQuantityValue(id: Int): Product?

}
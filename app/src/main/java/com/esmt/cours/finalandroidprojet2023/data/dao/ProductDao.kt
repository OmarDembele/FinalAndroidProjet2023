package com.esmt.cours.finalandroidprojet2023.data.dao

import androidx.room.*
import com.esmt.cours.finalandroidprojet2023.domain.entities.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(product: Product)

    @Delete
    suspend fun delete(product: Product)

    @Update
    fun updateProduct(product: Product)

    @Query("SELECT * FROM product ")
    fun getProduct(): Flow<List<Product>>

    @Query("SELECT * FROM product ORDER BY name ASC")
    fun getProductsOrderedByName(): Flow<List<Product>>

    @Query("SELECT * FROM product WHERE id = :id")
    suspend fun getProductById(id: Int): Product?

    @Query("UPDATE product SET quantity = quantity - :quantity WHERE id = :id")
    fun updateProductQuantity(id: Int, quantity: Int)

    @Query("UPDATE product SET quantity = :quantity WHERE id = :id")
    fun updateProductByQuantity(id: Int, quantity: Int)

}
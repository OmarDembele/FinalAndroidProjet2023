package com.esmt.cours.finalandroidprojet2023.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.esmt.cours.finalandroidprojet2023.data.dao.ProductDao
import com.esmt.cours.finalandroidprojet2023.domain.entities.Product
import com.esmt.cours.finalandroidprojet2023.domain.entities.ProductSold


@Database(entities = [Product::class, ProductSold::class], version = 1, exportSchema = false)
abstract class ProductDatabase : RoomDatabase(){
        abstract val productDao: ProductDao

        companion object {
                const val DATABASE_NAME = "product_db"
        }
}
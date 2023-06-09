package com.esmt.cours.finalandroidprojet2023.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.esmt.cours.finalandroidprojet2023.data.dao.ProductDao
import com.esmt.cours.finalandroidprojet2023.domain.entities.Product


@Database(entities = [Product::class], version = 1)
abstract class ProductDatabase : RoomDatabase(){
    abstract  val productDao: ProductDao
}
package com.esmt.cours.finalandroidprojet2023.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.esmt.cours.finalandroidprojet2023.data.dao.ProductSoldDao
import com.esmt.cours.finalandroidprojet2023.domain.entities.ProductSold


@Database(entities = [ProductSold::class], version = 1)
abstract class ProductSoldDatabase : RoomDatabase(){
    abstract  val productSoldDao: ProductSoldDao
}

package com.esmt.cours.finalandroidprojet2023.di

import android.app.Application
import androidx.room.Room
import com.esmt.cours.finalandroidprojet2023.data.dao.ProductDao
import com.esmt.cours.finalandroidprojet2023.data.database.ProductDatabase
import com.esmt.cours.finalandroidprojet2023.data.repository.ProductRepository
import com.esmt.cours.finalandroidprojet2023.data.repository.ProductRepositoryImpl
import com.esmt.cours.finalandroidprojet2023.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideProductDatabase(app: Application): ProductDatabase {
        return Room.databaseBuilder(
            app,
            ProductDatabase::class.java,
            ProductDatabase.DATABASE_NAME
        ).build()
    }


    @Provides
    @Singleton
    fun provideProductUseCase(repository: ProductRepository): ProductUseCase {
        return ProductUseCase(
            getProducts = GetProducts(repository = repository),
            deleteProduct = DeleteProducts(repository = repository),
            addProduct = AddProduct(repository = repository),
            getProduct = GetProduct(repository = repository),
            getProductsOrderedByName = GetProductOrderedByName(repository = repository),
            updateProductQuantity = UpdateProductQuantity (repository = repository),
            updateProductByQuantity = UpdateProductByQuantity (repository = repository)
        )
    }
}

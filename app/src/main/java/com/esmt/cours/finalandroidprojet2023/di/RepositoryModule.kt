package com.esmt.cours.finalandroidprojet2023.di


import android.app.Application
import com.esmt.cours.finalandroidprojet2023.data.dao.ProductDao
import com.esmt.cours.finalandroidprojet2023.data.database.ProductDatabase
import com.esmt.cours.finalandroidprojet2023.data.repository.ProductRepository
import com.esmt.cours.finalandroidprojet2023.data.repository.ProductRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    @JvmStatic
    fun provideProductRepository(productDao: ProductDao, app: Application): ProductRepository {
        return ProductRepositoryImpl(productDao, app)
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideProductDao(database: ProductDatabase): ProductDao {
        return database.productDao
    }
}

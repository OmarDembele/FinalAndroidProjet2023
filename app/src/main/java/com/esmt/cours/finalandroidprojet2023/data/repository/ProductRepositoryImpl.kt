package com.esmt.cours.finalandroidprojet2023.data.repository

import android.app.Application
import com.esmt.cours.finalandroidprojet2023.R
import com.esmt.cours.finalandroidprojet2023.data.dao.ProductDao
import com.esmt.cours.finalandroidprojet2023.domain.entities.Product
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val dao: ProductDao,
    private val appContext: Application
) : ProductRepository {

    init {
        val appName = appContext.getString(R.string.app_name)
        println("Hello from the repository. The app name is $appName")
    }

    override suspend fun upsert(product: Product) {
        return dao.upsert(product = product)
    }

    override suspend fun delete(product: Product) {
        return dao.delete(product = product)
    }

    override fun getProduct(): Flow<List<Product>> {
        return dao.getProduct()
    }

    override fun getProductsOrderedByName(): Flow<List<Product>> {
        return dao.getProductsOrderedByName()
    }

    override suspend fun getProductById(id: Int): Product? {
        return dao.getProductById(id = id)
    }

    override suspend fun updateProductQuantity(id: Int, quantity: Int): Job {
        return GlobalScope.launch {
            dao.updateProductQuantity(id, quantity)
        }
    }

    override suspend fun updateProductByQuantity(id: Int, quantity: Int): Job {
        return GlobalScope.launch {
            dao.updateProductByQuantity(id, quantity)
            }
        }



    /*
    override suspend fun updateProductQuantite(id: Int, quantity: Int) {
        val product = dao.getProductById(id)
        if (product != null) {
            product.quantity = quantity
            dao.updateProduct(product)
        }
    }*/

}
package com.esmt.cours.finalandroidprojet2023.domain.use_case

import com.esmt.cours.finalandroidprojet2023.data.repository.ProductRepository
import com.esmt.cours.finalandroidprojet2023.domain.entities.Product
import com.esmt.cours.finalandroidprojet2023.domain.entities.SortType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetProducts (
    private val repository: ProductRepository
) {
    operator fun invoke(
        sortType: SortType = SortType.NAME
    ): Flow<List<Product>> {
        return repository.getProduct().map { products ->
            when(sortType) {
                SortType.NAME -> products.sortedBy { it.name }
                SortType.PRICE -> products.sortedBy { it.price }
                SortType.QUANTITY -> products.sortedBy { it.quantity }
                SortType.QUANTITYSEUIL -> products.sortedBy { it.quantitySeuil }
            }
        }
    }
}
package com.esmt.cours.finalandroidprojet2023.domain.use_case

import com.esmt.cours.finalandroidprojet2023.data.repository.ProductRepository
import com.esmt.cours.finalandroidprojet2023.domain.entities.Product
import com.esmt.cours.finalandroidprojet2023.domain.entities.SortType
import com.esmt.cours.finalandroidprojet2023.domain.entities.SortType.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetProductOrderedByName (
    private val repository: ProductRepository
) {
    operator fun invoke(
        sortType: SortType = NAME
    ): Flow<List<Product>> {
        return repository.getProduct().map { products ->
            when(sortType) {
                NAME -> products.sortedBy { it.name }
                PRICE -> TODO()
                QUANTITY -> TODO()
                QUANTITYSEUIL -> TODO()
            }
        }
    }
}
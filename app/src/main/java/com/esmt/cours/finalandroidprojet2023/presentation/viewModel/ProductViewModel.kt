package com.esmt.cours.finalandroidprojet2023.presentation.viewModel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esmt.cours.finalandroidprojet2023.domain.model.ProductState
import com.esmt.cours.finalandroidprojet2023.domain.entities.Product
import com.esmt.cours.finalandroidprojet2023.domain.entities.SortType
import com.esmt.cours.finalandroidprojet2023.domain.use_case.ProductEvent
import com.esmt.cours.finalandroidprojet2023.domain.use_case.ProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productUseCases: ProductUseCase,
): ViewModel() {

    private val _sortType = MutableStateFlow(SortType.NAME)

    private val _products = _sortType
        .flatMapLatest { sortType ->
            when(sortType) {
                SortType.NAME -> productUseCases.getProductsOrderedByName()
                SortType.PRICE -> TODO()
                SortType.QUANTITY -> TODO()
                SortType.QUANTITYSEUIL -> TODO()
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())


    private val _state = MutableStateFlow(ProductState())
    val state = combine(_state, _sortType, _products) { state, sortType, products ->
        state.copy(
            products = products,
            sortType = sortType
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ProductState())

    fun onEvent(event: ProductEvent) {
        when(event) {
            is ProductEvent.DeleteProduct -> {
                viewModelScope.launch {
                    productUseCases.deleteProduct(event.product)
                }
            }

            is ProductEvent.GetProduct -> {
                viewModelScope.launch {
                    productUseCases.getProducts()
                }
            }

            is ProductEvent.SaveProduct -> {
                val name = state.value.name
                val price = state.value.price
                val quantity = state.value.quantity
                val quantitySeuil = state.value.quantitySeuil
                if (name.isBlank()) {
                    return
                }
                if (price < 0.0) {
                    return
                }
                if (quantity < 0) {
                    return
                }
                if (quantitySeuil < 0) {
                    return
                }

                val product = Product(
                    name = name,
                    price = price,
                    quantity = quantity,
                    quantitySeuil = quantitySeuil,
                )
                viewModelScope.launch {
                    productUseCases.addProduct(product)
                }

                _state.update {
                    it.copy(
                        isAddingProduct = false,
                        name = "",
                        price = 0.0,
                        quantity = 0,
                        quantitySeuil = 0
                    ) }
            }

            is ProductEvent.ModifyName -> {
                _state.update { it.copy(name = event.name) }
            }

            is ProductEvent.ModifyPrice -> {
                _state.update { it.copy(price = event.price) }
            }

            is ProductEvent.ModifyQuantity -> {
                _state.update { it.copy(quantity = event.quantity) }
            }

            is ProductEvent.ModifyQuantitySeuil -> {
                _state.update { it.copy(quantitySeuil = event.quantitySeuil) }
            }

            is ProductEvent.SortProducts -> {
                _sortType.value = event.sortType
            }
            is ProductEvent.UpdateProductQuantity -> {
                viewModelScope.launch {
                    productUseCases.updateProductQuantity(event.id, event.quantity)
                }
            }
            is ProductEvent.UpdateProductByQuantity -> {
                viewModelScope.launch {
                    productUseCases.updateProductByQuantity(event.id, event.quantity)
                }
            }
        }
    }
}

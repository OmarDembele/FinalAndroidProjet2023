package com.esmt.cours.finalandroidprojet2023.domain.use_case


data class ProductUseCase (
    val getProducts: GetProducts,
    val deleteProduct: DeleteProducts,
    val addProduct: AddProduct,
    val getProduct: GetProduct,
    val getProductsOrderedByName: GetProductOrderedByName,
    val updateProductQuantity: UpdateProductQuantity,
    val updateProductByQuantity: UpdateProductByQuantity
)

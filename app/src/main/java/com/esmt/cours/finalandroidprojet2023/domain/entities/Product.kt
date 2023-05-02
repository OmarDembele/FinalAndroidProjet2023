package com.esmt.cours.finalandroidprojet2023.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    val name: String,
    val price: Double,
    var quantity: Int,
    val quantitySeuil: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)

class InvalidProductException(message: String) : Exception(message)
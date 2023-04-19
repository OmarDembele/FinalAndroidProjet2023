package com.esmt.cours.finalandroidprojet2023.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey(autoGenerate = true) val id: Int? = 0,
    val name: String?,
    val price: Long?,
    val quantity: String?,
    val StringSeuil: String?,
)
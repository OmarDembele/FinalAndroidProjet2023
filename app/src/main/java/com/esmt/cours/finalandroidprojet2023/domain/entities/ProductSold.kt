package com.esmt.cours.finalandroidprojet2023.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date


@Entity
data class ProductSold(
    @PrimaryKey(autoGenerate = true) val id: Int? = 0,
    val name: String?,
    val price: Long?,
    val quantity: Int?,
    val date: String?
)

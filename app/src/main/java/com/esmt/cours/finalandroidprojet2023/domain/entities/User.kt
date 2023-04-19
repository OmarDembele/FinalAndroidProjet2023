package com.esmt.cours.finalandroidprojet2023.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class User (
    @PrimaryKey(autoGenerate = true) val id: Int? = 0,
    val firstName: String?,
    val lastName: String?,
    val email: String?,
    val password: String?,
    val confirmPassword: String?

    )
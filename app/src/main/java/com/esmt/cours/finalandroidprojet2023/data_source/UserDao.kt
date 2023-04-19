package com.esmt.cours.finalandroidprojet2023.data_source

import androidx.room.*
import com.esmt.cours.finalandroidprojet2023.domain.entities.User
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDao {

        @Insert
        suspend fun insert(user: User): Long

        @Update
        suspend fun update(user: User)

        @Delete
        suspend fun delete(user: User)

        @Query("SELECT * FROM user ")
        fun getUser(): Flow<List<User>>

        @Query("SELECT * FROM user WHERE email = :email AND password = :password")
        suspend fun getUserByEmailAndPassword(email: String, password: String): User?

        @Query("SELECT * FROM user WHERE id = :id")
        suspend fun getUserById(id: Int): User?
}

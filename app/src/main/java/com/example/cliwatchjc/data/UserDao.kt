package com.example.cliwatchjc.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User): Long

    @Query("SELECT * FROM User WHERE userId = :userId")
    fun getUserById(userId: Int): User?

    @Update
    suspend fun updateUser(user: User): Int

    @Query("DELETE FROM User WHERE userId = :userId")
    fun deleteUser(userId: Int): Int

    @Query("SELECT * FROM User WHERE userName = :userName AND password = :password")
    fun getUser(userName: String, password: String): User?
}
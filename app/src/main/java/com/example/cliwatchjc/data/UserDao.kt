package com.example.cliwatchjc.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {
    @Query("SELECT * FROM user WHERE id = :userId")
    fun getUserById(userId: Int): User?

    @Insert
    fun insertUser(user: User): Long

    @Update
    fun updateUser(user: User)

    @Query("DELETE FROM user WHERE id = :userId")
    fun deleteUser(userId: Int)
}
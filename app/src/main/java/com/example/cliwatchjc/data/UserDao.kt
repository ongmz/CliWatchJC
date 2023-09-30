package com.example.cliwatchjc.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {
    @Query("SELECT * FROM user WHERE userId = :userId")
    fun getUserById(userId: Long): User
    @Insert
    fun insertUser(user: User): Long

    @Update
    fun updateUser(user: User)

    @Query("DELETE FROM User WHERE userId = :userId")
    fun deleteUser(userId: Long)
}

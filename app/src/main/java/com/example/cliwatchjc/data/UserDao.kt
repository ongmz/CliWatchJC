package com.example.cliwatchjc.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update

@Dao
interface UserDao {

    @Transaction
    fun registerUser(user: User): Long {
        return insertUser(user)
    }

    @Query("SELECT * FROM User WHERE userId = :userId")
    fun getUserById(userId: Int): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User): Long

    @Update
    fun updateUser(user: User)

    @Query("DELETE FROM User WHERE userId = :userId")
    fun deleteUser(userId: Int)

    @Query("SELECT * FROM User WHERE userName = :userName AND password = :password")
    fun getUser(userName: String, password: String): User?
}

package com.seytkalievm.angimehubnative.storage

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.seytkalievm.angimehubnative.models.User


@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    suspend fun getUser(): User?

    @Insert
    suspend fun insertUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)
}
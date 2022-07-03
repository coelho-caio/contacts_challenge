package com.picpay.desafio.android.data.local

import androidx.room.Insert
import androidx.room.Query
import com.picpay.desafio.android.data.local.entity.UserEntity

interface UserDao {
    @Query(
        "SELECT * FROM user"
    )
    fun getAllUsers(): List<UserEntity>

    @Insert
    suspend fun insertUsers(user: UserEntity)
}
package com.example.redmuriapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = AppDatabase.USERS_TABLE_NAME)
data class UserDbModel(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String = "123456",
    val location: String = "Moscow",
    val profileImage: String = "",
    @PrimaryKey (autoGenerate = true)
    val id: Int = 0,
)

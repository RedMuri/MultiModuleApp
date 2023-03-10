package com.example.redmuriapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = AppDatabase.USERS_TABLE_NAME)
data class UserDbModel(
    @PrimaryKey
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val profileImage: String,
)

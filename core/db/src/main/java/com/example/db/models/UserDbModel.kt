package com.example.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.db.AppDatabase

@Entity(tableName = AppDatabase.USERS_TABLE_NAME)
data class UserDbModel(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String = "123456",
    val location: String = "Moscow",
    val profileImage: String? = null,
    @PrimaryKey (autoGenerate = true)
    val id: Int = 0,
)

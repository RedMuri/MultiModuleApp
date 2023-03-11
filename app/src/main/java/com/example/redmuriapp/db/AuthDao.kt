package com.example.redmuriapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AuthDao {

    @Query("select * from users where firstName == :firstName")
    suspend fun getUserByFirstName(firstName: String): UserDbModel?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addUser(userDbModel: UserDbModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun editUser(userDbModel: UserDbModel)
}
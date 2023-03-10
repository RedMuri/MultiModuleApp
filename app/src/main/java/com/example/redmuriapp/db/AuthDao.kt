package com.example.redmuriapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AuthDao {

    @Query("select * from users where id == :userId limit 1")
    fun getUserById(userId: Int): LiveData<UserDbModel>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addUser(userDbModel: UserDbModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun editUser(userDbModel: UserDbModel)

    @Query("delete from users where id=:userId")
    suspend fun deleteUserById(userId: String)
}
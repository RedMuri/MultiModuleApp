package com.example.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.db.models.UserDbModel

@Dao
interface UsersDao {

    @Query("select * from users where firstName == :firstName")
    suspend fun getUserByFirstName(firstName: String): UserDbModel?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addUser(userDbModel: UserDbModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun editUser(userDbModel: UserDbModel)

    @Query("delete from users where firstName=:firstName")
    suspend fun deleteUserByFirstName(firstName: String)
}
package com.test.testapp.store

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM user WHERE username =:userName AND password=:password")
  suspend fun findByName(userName: String,password:String): User?

    @Query("SELECT * FROM user WHERE username =:userName")
    suspend fun findOnlyByName(userName: String): User?

    @Insert
    suspend fun insert(user: User):Long

}
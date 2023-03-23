package com.test.testapp.store

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
    data class User(
    @PrimaryKey(autoGenerate = true) val id: Int?=null,
    @ColumnInfo(name = "username") val userName: String,
    @ColumnInfo(name = "password") val password: String,
    @ColumnInfo(name = "email") val email: String
)

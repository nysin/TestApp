package com.test.testapp.store

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1,exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract val userDao: UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null
        val DB_NAME = "testApp"


        fun getInstance(context: Context): AppDataBase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext, AppDataBase::class.java,
                        DB_NAME
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }

        }
    }


}
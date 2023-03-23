package com.test.testapp.store

import kotlinx.coroutines.flow.Flow

class UserRespository(private var userDao:UserDao) {

   suspend fun insert(user:User) :Long {
     return userDao.insert(user)
    }

     suspend fun login(userName:String, password:String): User? {
        return userDao.findByName(userName,password)
    }

    suspend fun findUserOnlyByUsername(userName: String):User? {
        return userDao.findOnlyByName(userName)
    }

}
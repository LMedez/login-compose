package com.luc.login.data.local

import com.luc.login.data.local.dao.UserDao
import com.luc.login.data.local.entities.asUserEntity
import com.luc.login.domain.model.User

class LocalDataSource(private val userDao: UserDao) {
    suspend fun getUserEntity(id: String) = userDao.getUserEntity(id)
    suspend fun saveUser(user: User) = userDao.insert(user.asUserEntity())
}
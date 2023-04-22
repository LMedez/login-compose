package com.luc.login.data.repository

import com.luc.login.data.ResultStatus
import com.luc.login.data.local.LocalDataSource
import com.luc.login.data.remote.firebase.firestore.FirestoreDataSource
import com.luc.login.domain.UserRepository
import com.luc.login.domain.model.User
import kotlinx.coroutines.delay

class UserRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val firestoreDataSource: FirestoreDataSource
) : UserRepository {
    override suspend fun getUser(id: String): ResultStatus<User> {
        return try {
            // This is the normal code you call when room has data
            // val localData = localDataSource.getUserEntity(id).asUser()
            delay(3000)
            ResultStatus.Success(User("Model Data", "",""))
        } catch (e: Exception) {
            ResultStatus.Error(e)
        }
    }

    override suspend fun saveUser(user: User) {
        localDataSource.saveUser(user)
    }
}

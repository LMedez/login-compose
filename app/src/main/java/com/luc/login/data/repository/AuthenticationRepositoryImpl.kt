package com.luc.login.data.repository

import com.luc.login.data.ResultStatus
import com.luc.login.data.remote.firebase.auth.AuthenticationDataSource
import com.luc.login.domain.AuthenticationRepository
import com.luc.login.domain.model.User

class AuthenticationRepositoryImpl(
    private val authenticationDataSource: AuthenticationDataSource
) : AuthenticationRepository {

    override suspend fun signInDefault(email: String, password: String): ResultStatus<User> {
        return authenticationDataSource.signInDefault(email, password)
    }

    override suspend fun signUpDefault(email: String, password: String): ResultStatus<User> {
       return authenticationDataSource.signUpDefault(email, password)
    }

    override suspend fun signUpWithGoogle(token: String): ResultStatus<User> {
        return authenticationDataSource.signUpWithGoogle(token)
    }

    override suspend fun signUpWithFacebook(token: String): ResultStatus<User> {
        TODO("Not yet implemented")
    }
}
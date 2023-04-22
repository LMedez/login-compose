package com.luc.login.domain

import com.luc.login.data.ResultStatus
import com.luc.login.domain.model.User

interface AuthenticationRepository {

    suspend fun signInDefault(email: String, password: String): ResultStatus<User>
    suspend fun signUpDefault(email: String, password: String): ResultStatus<User>

    suspend fun signUpWithGoogle(token: String): ResultStatus<User>
    suspend fun signUpWithFacebook(token: String): ResultStatus<User>
}
package com.luc.login.data.remote.firebase.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.luc.login.data.ResultStatus
import com.luc.login.domain.model.User
import kotlinx.coroutines.tasks.await

class AuthenticationDataSource(private val firebaseAuth: FirebaseAuth) {

    suspend fun signInDefault(email: String, password: String): ResultStatus<User> {
        return try {
            val data = firebaseAuth
                .signInWithEmailAndPassword(email, password)
                .await()
            val user = data.user ?: throw Exception()
            ResultStatus.Success(User(user.uid, user.displayName ?: "No display name", user.email!!))
        } catch (e: Exception) {
            ResultStatus.Error()
        }
    }

    suspend fun signUpDefault(email: String, password: String): ResultStatus<User> {
        return try {
            val data = firebaseAuth
                .createUserWithEmailAndPassword(email, password)
                .await()
            val user = data.user ?: throw Exception()
            ResultStatus.Success(User(user.uid, user.displayName ?: "No display name", user.email!!))
        } catch (e: Exception) {
            ResultStatus.Error()
        }
    }

    suspend fun signUpWithGoogle(token: String): ResultStatus<User> {
        return try {
            val credentials = GoogleAuthProvider.getCredential(token, null)
            val data = firebaseAuth
                .signInWithCredential(credentials)
                .await()
            val user = data.user ?: throw Exception()
            ResultStatus.Success(User(user.uid, user.displayName ?: "No display name", user.email!!))
        } catch (e: Exception) {
            ResultStatus.Error()
        }
    }
}
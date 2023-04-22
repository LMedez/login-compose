/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.luc.login.data.di

import android.app.Application
import com.luc.login.data.local.LocalDataSource
import com.luc.login.domain.UserRepository
import com.luc.login.data.local.LocalDatabase
import com.luc.login.data.local.dao.UserDao
import com.luc.login.data.remote.firebase.firestore.FirestoreDataSource
import com.luc.login.data.repository.UserRepositoryImpl
import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.luc.login.data.remote.firebase.auth.AuthenticationDataSource
import com.luc.login.data.repository.AuthenticationRepositoryImpl
import com.luc.login.domain.AuthenticationRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

private val firestoreModule = module {
    single { FirebaseFirestore.getInstance() }
    single {
        FirestoreDataSource(
            firebaseFirestore = get()
        )
    }
}

private val authenticationModule = module {
    single { FirebaseAuth.getInstance() }
    single {
        AuthenticationDataSource(
            firebaseAuth = get()
        )
    }
}

private val repositoryModule = module {
    factory { LocalDataSource(get()) }
    factory<UserRepository> {
        UserRepositoryImpl(get(), get())
    }
    factory<AuthenticationRepository> {
        AuthenticationRepositoryImpl(get())
    }
}

private val roomModule = module {
    fun provideDatabase(application: Application): LocalDatabase {
        return Room.databaseBuilder(application, LocalDatabase::class.java, "db")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideUserDao(database: LocalDatabase): UserDao {
        return database.userDao()
    }

    single { provideDatabase(androidApplication()) }
    single { provideUserDao(get()) }
}

val dataModule = listOf(roomModule, firestoreModule, authenticationModule, repositoryModule)
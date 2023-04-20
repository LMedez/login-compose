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

package com.luc.login.presentation.viewmodel

import com.luc.login.data.ResultStatus
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luc.login.domain.UserRepository
import com.luc.login.domain.model.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

class UserViewModel(
    private val userRepository: UserRepository
) : ViewModel() {

    private val getUser = flow {
        when (val result = userRepository.getUser("1")) {
            is ResultStatus.Success -> emit(UserUiState(user = result.data))
            is ResultStatus.Error -> emit(
                UserUiState(
                    error = result.exception?.message ?: "Unknown error"
                )
            )
        }
    }

    private val delay = flow {
        emit(true)
        delay(4000)
        emit(false)
    }

    val state: StateFlow<UserUiState> = combine(delay, getUser) { delay, userData ->
        UserUiState(showDelay = delay, user = userData.user)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), initialValue = UserUiState())

}

data class UserUiState(
    val isUserLogged: Boolean = false,
    val user: User? = null,
    val showDelay: Boolean = true,
    val error: String? = null
)


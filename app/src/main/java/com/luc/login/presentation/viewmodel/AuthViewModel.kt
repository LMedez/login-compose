package com.luc.login.presentation.viewmodel

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luc.login.data.ResultStatus
import com.luc.login.domain.AuthenticationRepository
import com.luc.login.domain.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authenticationRepository: AuthenticationRepository
) : ViewModel() {

    private val _userCredentialsStatus = MutableStateFlow(UserCredentialStatusState())
    val userCredentialsStatus = _userCredentialsStatus.asStateFlow()

    private val _user = MutableStateFlow(UserAuthUiState(loading = true))
    val user = _user.asStateFlow()

    fun signInDefault(email: String, password: String) {
        viewModelScope.launch {
            when (val result = authenticationRepository.signInDefault(email, password)) {
                is ResultStatus.Error -> _user.update {
                    it.copy(
                        error = result.exception?.message,
                        loading = false
                    )
                }
                is ResultStatus.Success -> _user.update {
                    it.copy(
                        user = result.data,
                        loading = false
                    )
                }
            }
        }
    }

    fun signUpWithGoogle(idToken: String) {
        viewModelScope.launch {
            when (val result = authenticationRepository.signUpWithGoogle(idToken)) {
                is ResultStatus.Error -> _user.update {
                    it.copy(
                        error = result.exception?.message,
                        loading = false
                    )
                }
                is ResultStatus.Success -> _user.update {
                    it.copy(
                        user = result.data,
                        loading = false
                    )
                }
            }
        }
    }

    fun signUpWithFacebook(idToken: String) {
        viewModelScope.launch {
            when (val result = authenticationRepository.signUpWithFacebook(idToken)) {
                is ResultStatus.Error -> _user.update {
                    it.copy(
                        error = result.exception?.message,
                        loading = false
                    )
                }
                is ResultStatus.Success -> _user.update {
                    it.copy(
                        user = result.data,
                        loading = false
                    )
                }
            }
        }
    }
    fun validateEmail(email: String) {
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches())
            _userCredentialsStatus.update {
                it.copy(
                    emailErrMsg = "", isEmailValid = true, email = email
                )
            }
        else _userCredentialsStatus.update {
            it.copy(
                emailErrMsg = "Insert proper email",
                isEmailValid = false
            )
        }
    }

    fun validateUserName(userName: String) {
        if (userName.length in 3..18)
            _userCredentialsStatus.update {
                it.copy(
                    passwordErrMsg = "", isPasswordValid = true
                )
            }
        else _userCredentialsStatus.update {
            it.copy(
                passwordErrMsg = "Insert password large",
                isPasswordValid = false
            )
        }
    }

    fun validatePassword(password: String) {
        if (password.length in 4..9)
            _userCredentialsStatus.update {
                it.copy(
                    passwordErrMsg = "", isPasswordValid = true, password = password
                )
            }
        else _userCredentialsStatus.update {
            it.copy(
                passwordErrMsg = "Insert password large",
                isPasswordValid = false
            )
        }
    }


    fun signUpDefault(email: String, password: String) {
        viewModelScope.launch {
            when (val result = authenticationRepository.signUpDefault(email, password)) {
                is ResultStatus.Error -> _user.update {
                    it.copy(
                        error = result.exception?.message,
                        loading = false
                    )
                }
                is ResultStatus.Success -> _user.update {
                    it.copy(
                        user = result.data,
                        loading = false,
                    )
                }
            }
        }
    }
}

data class UserAuthUiState(
    val user: User? = null,
    val loading: Boolean = false,
    val error: String? = null
)

data class UserCredentialStatusState(
    val email: String = "",
    val password: String = "",
    val userName: String = "",
    val emailErrMsg: String = "",
    val passwordErrMsg: String = "",
    val isEmailValid: Boolean = false,
    val isPasswordValid: Boolean = false,
)

package com.luc.login.domain.model

import com.luc.login.data.local.entities.UserEntity

data class User(val uid: String, val name: String, val email: String)

fun UserEntity.asUser() = User(
    this.uid,
    this.name,
    this.email
)

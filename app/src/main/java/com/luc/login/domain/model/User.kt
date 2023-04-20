package com.luc.login.domain.model

import com.luc.login.data.local.entities.UserEntity

data class User(val name: String, val uid: Int)

fun UserEntity.asUser() = User(
    this.name,
    this.uid
)

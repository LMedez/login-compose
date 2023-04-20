package com.luc.login.data.local.dao

import com.luc.login.data.local.entities.UserEntity
import androidx.room.Dao
import androidx.room.Query

@Dao
abstract class UserDao: BaseDao<UserEntity> {

    // TODO("create the necessary abstract methods for this model")

    @Query("SELECT * FROM userentity WHERE uid = :uid")
    abstract suspend fun getUserEntity(uid: String): UserEntity

}

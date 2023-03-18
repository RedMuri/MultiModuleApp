package com.example.data.mappers

import com.example.data.model.User
import javax.inject.Inject

class UsersMapper @Inject constructor() {

    fun userDbModelToEntity(userDbModel: com.example.db.models.UserDbModel) =
        User(
            id = userDbModel.id,
            firstName = userDbModel.firstName,
            lastName = userDbModel.lastName,
            email = userDbModel.email,
            password = userDbModel.password,
            location = userDbModel.location,
            profileImage = userDbModel.profileImage,
        )

    fun userEntityToDbModel(user: User) = com.example.db.models.UserDbModel(
        id = user.id,
        firstName = user.firstName,
        lastName = user.lastName,
        email = user.email,
        password = user.password,
        location = user.location,
        profileImage = user.profileImage,
    )
}
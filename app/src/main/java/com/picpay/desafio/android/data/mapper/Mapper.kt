package com.picpay.desafio.android.data.mapper

import com.picpay.desafio.android.data.local.entity.UserEntity
import com.picpay.desafio.android.data.remote.model.UserResponse
import com.picpay.desafio.android.domain.model.User

fun UserResponse.toEntity() = UserEntity(
    id = this.id,
    img = this.img ?: "",
    name = this.name?: "",
    username = this.username?: ""
)

fun UserEntity.toResponse() = UserResponse(
    id = this.id,
    img = this.img,
    name = this.name,
    username = this.username
)

fun UserResponse.toDomain() = User(
    id = this.id,
    img = this.img?: "",
    name = this.name?: "",
    username = this.username?: ""
)

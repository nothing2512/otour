package com.example.otour.data.source.response

data class UserResponse(
    val userId: Int,
    val name: String,
    val email: String,
    var password: String,
    var photo: String,
    val role: Int
)
package com.example.otour.data.source.response

data class MainResponse<T>(
    val status: Boolean,
    val message: String,
    val code: Int,
    var data: T
)
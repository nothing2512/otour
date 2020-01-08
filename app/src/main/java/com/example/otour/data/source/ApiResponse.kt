package com.example.otour.data.source

import android.util.Log
import retrofit2.Response

@Suppress("unused")
sealed class ApiResponse<T> {
    companion object {
        fun <T> create(error: Throwable): ApiErrorResponse<T> =
            ApiErrorResponse(error.message ?: "unknown error")

        fun <T> create(response: Response<T>): ApiResponse<T> =
            if (response.isSuccessful) {

                val body = response.body()

                if (body == null || response.code() == 204) ApiEmptyResponse()
                else ApiSuccessResponse(body)

            } else {

                Log.e("Robet", response.errorBody()?.string())

                val errorMsg =
                    if (response.errorBody()?.string().isNullOrEmpty()) response.message()
                    else response.errorBody()?.string()

                ApiErrorResponse(errorMsg ?: "unknown error")
            }
    }
}

class ApiEmptyResponse<T> : ApiResponse<T>()

data class ApiSuccessResponse<T>(val body: T) : ApiResponse<T>()

data class ApiErrorResponse<T>(val errorMessage: String) : ApiResponse<T>()

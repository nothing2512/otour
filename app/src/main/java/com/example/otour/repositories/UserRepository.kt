package com.example.otour.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.otour.data.source.ApiResponse
import com.example.otour.data.source.ApiSuccessResponse
import com.example.otour.data.source.RemoteService
import com.example.otour.data.source.response.MainResponse
import com.example.otour.data.source.response.UserResponse
import com.example.otour.utils.*
import com.example.otour.vo.Resource

class UserRepository(
    private val appExecutors: AppExecutors,
    private val remoteService: RemoteService
) {

    val authData = MutableLiveData<Resource<MainResponse<UserResponse>>>()
    val userData = MutableLiveData<Resource<MainResponse<UserResponse>>>()

    suspend fun login(email: String, password: String) {
        object : FetchNetwork<MainResponse<UserResponse>>(appExecutors) {

            override fun createCall(): LiveData<ApiResponse<MainResponse<UserResponse>>> {
                return remoteService.login(createAuth(), email, password)
            }

            override fun processResponse(response: ApiSuccessResponse<MainResponse<UserResponse>>): MainResponse<UserResponse> {
                val body = response.body
                try {
                    val data = body.data
                    data.password = password
                    data.photo = Constants.BASE_IMAGE_URL + data.photo
                    body.data = data
                } catch (e: Exception) {}
                return body
            }
        }.asLiveData().observeForever { authData.postValue(it) }
    }

    suspend fun register(name: String, email: String, password: String, photo: String) {
        object : FetchNetwork<MainResponse<UserResponse>>(appExecutors) {
            override fun createCall() = remoteService.register(
                createAuth(),
                getTextPart(name),
                getTextPart(email),
                getTextPart(password),
                getTextPart("0"),
                getImagePart("photo", photo)
            )

            override fun processResponse(response: ApiSuccessResponse<MainResponse<UserResponse>>): MainResponse<UserResponse> {
                val body = response.body
                val data = body.data
                data.password = password
                data.photo = Constants.BASE_IMAGE_URL + data.photo
                body.data = data
                return body
            }
        }.asLiveData().observeForever { authData.postValue(it) }
    }

    suspend fun updateUserWithImage(userId: Int, name: String, password: String, photo: String) {
        object : FetchNetwork<MainResponse<UserResponse>>(appExecutors) {
            override fun createCall() = remoteService.updateUser(
                createAuth(userId),
                getTextPart(name),
                getTextPart(password),
                getImagePart("photo", photo)
            )

            override fun processResponse(response: ApiSuccessResponse<MainResponse<UserResponse>>): MainResponse<UserResponse> {
                val body = response.body
                val data = body.data
                data.password = password
                data.photo = Constants.BASE_IMAGE_URL + data.photo
                body.data = data
                return body
            }
        }.asLiveData().observeForever { userData.postValue(it) }
    }

    suspend fun updateUserWithoutImage(userId: Int, name: String, password: String, photo: String) {
        object : FetchNetwork<MainResponse<UserResponse>>(appExecutors) {
            override fun createCall() =
                remoteService.updateUser(createAuth(userId), name, password, photo)

            override fun processResponse(response: ApiSuccessResponse<MainResponse<UserResponse>>): MainResponse<UserResponse> {
                val body = response.body
                val data = body.data
                data.password = password
                data.photo = Constants.BASE_IMAGE_URL + data.photo
                body.data = data
                return body
            }
        }.asLiveData().observeForever { userData.postValue(it) }
    }
}
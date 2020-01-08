package com.example.otour.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.otour.data.source.response.MainResponse
import com.example.otour.data.source.response.UserResponse
import com.example.otour.repositories.UserRepository
import com.example.otour.utils.MainPreference
import com.example.otour.utils.launchMain
import com.example.otour.vo.Resource

class AuthViewModel(
    private val userRepository: UserRepository,
    private val preference: MainPreference
) : ViewModel() {

    fun login(email: String, password: String): LiveData<Resource<MainResponse<UserResponse>>> {
        val login = userRepository.authData
        launchMain { userRepository.login(email, password) }
        return login
    }

    fun register(
        name: String,
        email: String,
        password: String,
        photo: String
    ): LiveData<Resource<MainResponse<UserResponse>>> {
        val register = userRepository.authData
        launchMain { userRepository.register(name, email, password, photo) }
        return register
    }

    fun setUser(user: UserResponse?) {
        preference.set("userId", user?.userId)
        preference.set("name", user?.name)
        preference.set("email", user?.email)
        preference.set("password", user?.password)
        preference.set("photo", user?.photo)
        preference.set("role", user?.role)
        preference.set("isLogin", true)
    }
}
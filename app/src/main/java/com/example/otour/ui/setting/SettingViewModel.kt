package com.example.otour.ui.setting

import androidx.lifecycle.ViewModel
import com.example.otour.data.source.response.MainResponse
import com.example.otour.data.source.response.UserResponse
import com.example.otour.repositories.UserRepository
import com.example.otour.utils.MainPreference
import com.example.otour.utils.launchMain
import com.example.otour.vo.Resource

class SettingViewModel(
    private val userRepository: UserRepository,
    private val preference: MainPreference
) : ViewModel() {

    val user = userRepository.userData

    init {
        user.postValue(
            Resource.success(
                MainResponse(
                    true,
                    "",
                    200,
                    UserResponse(
                        preference.getInt("userId"),
                        preference.getString("name"),
                        preference.getString("email"),
                        preference.getString("password"),
                        preference.getString("photo"),
                        preference.getInt("role")
                    )
                )
            )
        )
    }

    fun updateWithUploadImage(name: String, password: String, photo: String) {
        launchMain {
            userRepository.updateUserWithImage(
                preference.getInt("userId"),
                name,
                password,
                photo
            )
        }
    }

    fun updateWithoutUploadImage(name: String, password: String) {
        launchMain {
            userRepository.updateUserWithoutImage(
                preference.getInt("userId"),
                name,
                password,
                preference.getString("photo")
            )
        }
    }

    fun saveUser(user: UserResponse?) {
        preference.set("userId", user?.userId)
        preference.set("name", user?.name)
        preference.set("email", user?.email)
        preference.set("password", user?.password)
        preference.set("photo", user?.photo)
        preference.set("role", user?.role)
        preference.set("isLogin", true)
    }

    fun logout() {
        preference.clear()
    }
}
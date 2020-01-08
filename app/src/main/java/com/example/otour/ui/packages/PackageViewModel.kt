package com.example.otour.ui.packages

import androidx.lifecycle.ViewModel
import com.example.otour.repositories.PackageRepository
import com.example.otour.utils.MainPreference
import com.example.otour.utils.launchMain

class PackageViewModel(
    private val packageRepository: PackageRepository,
    private val preference: MainPreference
) : ViewModel() {

    val packages = packageRepository.packageData

    fun getDetail(packageId: Int) {
        launchMain { packageRepository.getDetail(preference.getInt("userId"), packageId) }
    }

    fun rate(packageId: Int, rate: Int) {
        launchMain { packageRepository.rate(preference.getInt("userId"), packageId, rate) }
    }
}
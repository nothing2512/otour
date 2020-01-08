package com.example.otour.ui.search

import androidx.lifecycle.ViewModel
import com.example.otour.repositories.PackageRepository
import com.example.otour.utils.MainPreference
import com.example.otour.utils.launchMain

class SearchViewModel(
    private val packageRepository: PackageRepository,
    private val preference: MainPreference
) : ViewModel() {

    val searchData = packageRepository.searchData

    fun search(keyword: String? = null, cityId: Int? = null, price: Int? = null) {
        launchMain { packageRepository.search(preference.getInt("userId"), keyword, cityId, price) }
    }
}
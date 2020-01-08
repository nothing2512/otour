package com.example.otour.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.otour.data.source.response.MainResponse
import com.example.otour.data.source.response.PackageResponse
import com.example.otour.repositories.PackageRepository
import com.example.otour.utils.MainPreference
import com.example.otour.utils.launchMain
import com.example.otour.vo.Resource

class HomeViewModel(
    private val packageRepository: PackageRepository,
    private val preference: MainPreference
) : ViewModel() {

    fun getPromo(): LiveData<Resource<MainResponse<List<PackageResponse>>>> {
        val promo = packageRepository.promoData
        launchMain { packageRepository.getPromo(preference.getInt("userId")) }
        return promo
    }

    fun getPopular(): LiveData<Resource<MainResponse<List<PackageResponse>>>> {
        val popular = packageRepository.popularData
        launchMain { packageRepository.getPopular(preference.getInt("userId")) }
        return popular
    }

    fun getBest(): LiveData<Resource<MainResponse<List<PackageResponse>>>> {
        val best = packageRepository.bestData
        launchMain { packageRepository.getBest(preference.getInt("userId")) }
        return best
    }
}
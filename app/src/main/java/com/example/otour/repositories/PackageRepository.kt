package com.example.otour.repositories

import androidx.lifecycle.MutableLiveData
import com.example.otour.data.source.ApiSuccessResponse
import com.example.otour.data.source.RemoteService
import com.example.otour.data.source.response.MainResponse
import com.example.otour.data.source.response.PackageResponse
import com.example.otour.utils.AppExecutors
import com.example.otour.utils.Constants
import com.example.otour.utils.FetchNetwork
import com.example.otour.utils.createAuth
import com.example.otour.vo.Resource

class PackageRepository(
    private val appExecutors: AppExecutors,
    private val remoteService: RemoteService
) {

    val packageData = MutableLiveData<Resource<MainResponse<PackageResponse>>>()
    val promoData = MutableLiveData<Resource<MainResponse<List<PackageResponse>>>>()
    val popularData = MutableLiveData<Resource<MainResponse<List<PackageResponse>>>>()
    val bestData = MutableLiveData<Resource<MainResponse<List<PackageResponse>>>>()
    val searchData = MutableLiveData<Resource<MainResponse<List<PackageResponse>>>>()

    suspend fun getDetail(userId: Int, packageId: Int) {
        object : FetchNetwork<MainResponse<PackageResponse>>(appExecutors) {
            override fun createCall() =
                remoteService.getDetailPackage(createAuth(userId), packageId)

            override fun processResponse(response: ApiSuccessResponse<MainResponse<PackageResponse>>): MainResponse<PackageResponse> {
                val body = response.body
                val data = body.data
                data.image = Constants.BASE_IMAGE_URL + data.image
                body.data = data
                return body
            }
        }.asLiveData().observeForever { packageData.postValue(it) }
    }

    suspend fun getPromo(userId: Int) {
        object : FetchNetwork<MainResponse<List<PackageResponse>>>(appExecutors) {
            override fun createCall() = remoteService.getPromoPackage(createAuth(userId))

            override fun processResponse(response: ApiSuccessResponse<MainResponse<List<PackageResponse>>>): MainResponse<List<PackageResponse>> {
                val body = response.body
                val data = ArrayList<PackageResponse>()
                body.data.forEach {
                    it.image = Constants.BASE_IMAGE_URL + it.image
                    data.add(it)
                }
                body.data = data
                return body
            }
        }.asLiveData().observeForever { promoData.postValue(it) }
    }

    suspend fun getPopular(userId: Int) {
        object : FetchNetwork<MainResponse<List<PackageResponse>>>(appExecutors) {
            override fun createCall() = remoteService.getPopularPackage(createAuth(userId))

            override fun processResponse(response: ApiSuccessResponse<MainResponse<List<PackageResponse>>>): MainResponse<List<PackageResponse>> {
                val body = response.body
                val data = ArrayList<PackageResponse>()
                body.data.forEach {
                    it.image = Constants.BASE_IMAGE_URL + it.image
                    data.add(it)
                }
                body.data = data
                return body
            }
        }.asLiveData().observeForever { popularData.postValue(it) }
    }

    suspend fun getBest(userId: Int) {
        object : FetchNetwork<MainResponse<List<PackageResponse>>>(appExecutors) {
            override fun createCall() = remoteService.getBestPackage(createAuth(userId))

            override fun processResponse(response: ApiSuccessResponse<MainResponse<List<PackageResponse>>>): MainResponse<List<PackageResponse>> {
                val body = response.body
                val data = ArrayList<PackageResponse>()
                body.data.forEach {
                    it.image = Constants.BASE_IMAGE_URL + it.image
                    data.add(it)
                }
                body.data = data
                return body
            }
        }.asLiveData().observeForever { bestData.postValue(it) }
    }

    suspend fun rate(userId: Int, packageId: Int, rate: Int) {
        object : FetchNetwork<MainResponse<PackageResponse>>(appExecutors) {
            override fun createCall() =
                remoteService.ratePackage(createAuth(userId), packageId, rate)

            override fun processResponse(response: ApiSuccessResponse<MainResponse<PackageResponse>>): MainResponse<PackageResponse> {
                val body = response.body
                val data = body.data
                data.image = Constants.BASE_IMAGE_URL + data.image
                body.data = data
                return body
            }
        }.asLiveData().observeForever { packageData.postValue(it) }
    }

    suspend fun search(userId: Int, keyword: String?, cityId: Int?, price: Int?) {
        object : FetchNetwork<MainResponse<List<PackageResponse>>>(appExecutors) {
            override fun createCall() =
                remoteService.searchPackage(createAuth(userId), keyword, cityId, price)

            override fun processResponse(response: ApiSuccessResponse<MainResponse<List<PackageResponse>>>): MainResponse<List<PackageResponse>> {
                val body = response.body
                val data = ArrayList<PackageResponse>()
                body.data.forEach {
                    it.image = Constants.BASE_IMAGE_URL + it.image
                    data.add(it)
                }
                body.data = data
                return body
            }
        }.asLiveData().observeForever { searchData.postValue(it) }
    }
}
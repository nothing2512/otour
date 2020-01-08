package com.example.otour.repositories

import androidx.lifecycle.MutableLiveData
import com.example.otour.data.source.RemoteService
import com.example.otour.data.source.response.BookingResponse
import com.example.otour.data.source.response.MainResponse
import com.example.otour.utils.AppExecutors
import com.example.otour.utils.FetchNetwork
import com.example.otour.utils.createAuth
import com.example.otour.vo.Resource

class BookingRepository(
    private val appExecutors: AppExecutors,
    private val remoteService: RemoteService
) {

    val bookingData = MutableLiveData<Resource<MainResponse<BookingResponse>>>()
    val historyData = MutableLiveData<Resource<MainResponse<List<BookingResponse>>>>()

    suspend fun booking(userId: Int, packageId: Int, amount: Int, payment: String) {
        object : FetchNetwork<MainResponse<BookingResponse>>(appExecutors) {
            override fun createCall() =
                remoteService.bookingPackage(createAuth(userId), packageId, amount, payment)
        }.asLiveData().observeForever { bookingData.postValue(it) }
    }

    suspend fun getDetail(userId: Int, packageId: Int) {
        object : FetchNetwork<MainResponse<BookingResponse>>(appExecutors) {
            override fun createCall() =
                remoteService.getBookingDetail(createAuth(userId), packageId)
        }.asLiveData().observeForever { bookingData.postValue(it) }
    }

    suspend fun purchase(userId: Int, bookingId: Int, description: String) {
        object : FetchNetwork<MainResponse<BookingResponse>>(appExecutors) {
            override fun createCall() =
                remoteService.purchaseBooking(createAuth(userId), bookingId, description)
        }.asLiveData().observeForever { bookingData.postValue(it) }
    }

    suspend fun getHistory(userId: Int) {
        object : FetchNetwork<MainResponse<List<BookingResponse>>>(appExecutors) {
            override fun createCall() = remoteService.getBookingHistory(createAuth(userId))
        }.asLiveData().observeForever { historyData.postValue(it) }
    }
}
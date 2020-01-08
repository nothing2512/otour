package com.example.otour.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.otour.data.source.response.BookingResponse
import com.example.otour.data.source.response.MainResponse
import com.example.otour.repositories.BookingRepository
import com.example.otour.utils.MainPreference
import com.example.otour.utils.launchMain
import com.example.otour.vo.Resource

class HistoryViewModel(
    private val bookingRepository: BookingRepository,
    private val preference: MainPreference
) : ViewModel() {

    fun getHistory(): LiveData<Resource<MainResponse<List<BookingResponse>>>> {
        val history = bookingRepository.historyData
        launchMain { bookingRepository.getHistory(preference.getInt("userId")) }
        return history
    }
}
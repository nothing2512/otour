package com.example.otour.ui.booking

import androidx.lifecycle.ViewModel
import com.example.otour.repositories.BookingRepository
import com.example.otour.utils.MainPreference
import com.example.otour.utils.launchMain

class BookingViewModel(
    private val bookingRepository: BookingRepository,
    private val preference: MainPreference
) : ViewModel() {

    val booking = bookingRepository.bookingData

    fun getDetail(bookingId: Int) {
        launchMain { bookingRepository.getDetail(preference.getInt("userId"), bookingId) }
    }

    fun purchase(bookingId: Int, description: String) {
        launchMain {
            bookingRepository.purchase(
                preference.getInt("userId"),
                bookingId,
                description
            )
        }
    }
}
package com.example.otour.ui.booking

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.otour.R
import com.example.otour.databinding.ActivityBookingBinding
import com.example.otour.utils.Constants
import com.example.otour.utils.getBinding
import com.example.otour.utils.launchMain
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookingBinding
    private val bookingViewModel: BookingViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getBinding(R.layout.activity_booking)
        launchMain { subscribeUI() }
    }

    private fun subscribeUI() {
        val bookingId = intent.getIntExtra(Constants.EXTRA_ID, 0)

        bookingViewModel.booking.observe(this, Observer {

        })

        bookingViewModel.getDetail(bookingId)
    }
}

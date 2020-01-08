package com.example.otour.di

import com.example.otour.data.source.RemoteService
import com.example.otour.data.source.adapter.CallAdapterFactory
import com.example.otour.repositories.BookingRepository
import com.example.otour.repositories.PackageRepository
import com.example.otour.repositories.UserRepository
import com.example.otour.ui.auth.AuthViewModel
import com.example.otour.ui.booking.BookingViewModel
import com.example.otour.ui.dashboard.DashboardViewModel
import com.example.otour.ui.history.HistoryViewModel
import com.example.otour.ui.home.HomeViewModel
import com.example.otour.ui.packages.PackageViewModel
import com.example.otour.ui.search.SearchViewModel
import com.example.otour.ui.setting.SettingViewModel
import com.example.otour.utils.AppExecutors
import com.example.otour.utils.Constants
import com.example.otour.utils.MainPreference
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AppModule {

    val mainModule = module {

        single <RemoteService> { provideService() }
        single { AppExecutors() }
        single { MainPreference(androidContext()) }
        single { BookingRepository(get(), get()) }
        single { PackageRepository(get(), get()) }
        single { UserRepository(get(), get()) }

        viewModel { AuthViewModel(get(), get()) }
        viewModel { BookingViewModel(get(), get()) }
        viewModel { DashboardViewModel() }
        viewModel { HistoryViewModel(get(), get()) }
        viewModel { HomeViewModel(get(), get()) }
        viewModel { PackageViewModel(get(), get()) }
        viewModel { SearchViewModel(get(), get()) }
        viewModel { SettingViewModel(get(), get()) }
    }

    private fun provideService() =
        Retrofit.Builder()
            .addCallAdapterFactory(CallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(RemoteService::class.java)
}
package com.example.otour.ui.dashboard

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.otour.ui.history.HistoryFragment
import com.example.otour.ui.home.HomeFragment
import com.example.otour.ui.search.SearchFragment
import com.example.otour.ui.setting.SettingFragment
import com.example.otour.utils.Constants

class DashboardViewModel : ViewModel() {

    private val _fragment = listOf(
        HomeFragment.newInstance(),
        SearchFragment.newInstance(),
        HistoryFragment.newInstance(),
        SettingFragment.newInstance()
    )

    val fragment = MutableLiveData<Fragment>().apply {
        postValue(_fragment[Constants.STATE_HOME])
    }

    fun setFragment(state: Int) {

        if (fragment.value == _fragment[state]) return

        fragment.postValue(_fragment[state])
    }
}
package com.example.otour.ui.dashboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.otour.R
import com.example.otour.databinding.ActivityDashboardBinding
import com.example.otour.utils.Constants
import com.example.otour.utils.getBinding
import com.example.otour.utils.launchMain
import com.example.otour.utils.setFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding
    private val dashboardViewModel: DashboardViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getBinding(R.layout.activity_dashboard)
        launchMain {
            binding.lifecycleOwner = this@DashboardActivity
            subscribeUI()
        }
    }

    private fun subscribeUI() {

        binding.navigation.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.nav_home -> {
                    dashboardViewModel.setFragment(Constants.STATE_HOME)
                    true
                }
                R.id.nav_search -> {
                    dashboardViewModel.setFragment(Constants.STATE_SEARCH)
                    true
                }
                R.id.nav_history -> {
                    dashboardViewModel.setFragment(Constants.STATE_HISTORY)
                    true
                }
                R.id.nav_profile -> {
                    dashboardViewModel.setFragment(Constants.STATE_SETTING)
                    true
                }
                else -> false
            }
        }

        dashboardViewModel.fragment.observe(this, Observer { setFragment(binding.content, it) })
        dashboardViewModel.setFragment(Constants.STATE_HOME)
    }
}

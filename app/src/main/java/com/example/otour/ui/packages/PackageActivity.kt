package com.example.otour.ui.packages

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.otour.R
import com.example.otour.databinding.ActivityPackageBinding
import com.example.otour.utils.getBinding
import com.example.otour.utils.launchMain
import org.koin.androidx.viewmodel.ext.android.viewModel

class PackageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPackageBinding
    private val packageViewModel: PackageViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getBinding(R.layout.activity_package)
        launchMain { subscribeUI() }
    }

    private fun subscribeUI() {

    }
}

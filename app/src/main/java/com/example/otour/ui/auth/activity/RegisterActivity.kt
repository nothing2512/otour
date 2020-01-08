package com.example.otour.ui.auth.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.otour.R
import com.example.otour.databinding.ActivityRegisterBinding
import com.example.otour.ui.auth.AuthViewModel
import com.example.otour.ui.dashboard.DashboardActivity
import com.example.otour.utils.*
import com.example.otour.vo.Status
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val authViewModel: AuthViewModel by viewModel()
    private val preference: MainPreference by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getBinding(R.layout.activity_register)
        launchMain { subscribeUI() }
    }

    private fun subscribeUI() {

        binding.btLogin.setOnClickListener { startActivity(LoginActivity::class.java) }

        binding.btRegister.setOnClickListener {

            when {
                binding.etUsername.text.isNullOrEmpty() -> {
                    binding.etUsername.error = "Username must not be null"
                }
                binding.etEmail.text.isNullOrEmpty() -> {
                    binding.etEmail.error = "Email must not be null"
                }
                binding.etPassword.text.isNullOrEmpty() -> {
                    binding.etPassword.error = "Password must not be null"
                }
                else -> {
                    register(
                        binding.etUsername.text.toString(),
                        binding.etEmail.text.toString(),
                        binding.etPassword.text.toString()
                    )
                }
            }
        }
    }

    private fun register(name: String, email: String, password: String) {
        authViewModel.register(name, email, password, "").observe(this, Observer {

            when {
                it.status == Status.ERROR -> toast(it.message)
                it.data?.status == false -> toast(it.data.message)
                else -> {
                    it.data?.data?.let { user ->
                        preference.set(Constants.ID_KEY, user.userId)
                        preference.set(Constants.NAME_KEY, user.name)
                        preference.set(Constants.EMAIL_KEY, user.email)
                        preference.set(Constants.PASS_KEY, user.password)
                        preference.set(Constants.PHOTO_KEY, user.photo)
                        preference.set(Constants.ROLE_KEY, user.role)

                        startActivity(DashboardActivity::class.java)
                        finish()
                    }
                }
            }
        })
    }
}

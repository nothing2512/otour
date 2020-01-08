package com.example.otour.ui.auth.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.otour.R
import com.example.otour.databinding.ActivityLoginBinding
import com.example.otour.ui.auth.AuthViewModel
import com.example.otour.ui.dashboard.DashboardActivity
import com.example.otour.utils.*
import com.example.otour.vo.Status
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val authViewModel: AuthViewModel by viewModel()
    private val preference: MainPreference by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getBinding(R.layout.activity_login)
        launchMain { subscribeUI() }
    }

    private fun subscribeUI() {

        binding.btRegister.setOnClickListener {
            startActivity(RegisterActivity::class.java)
        }

        binding.btLogin.setOnClickListener {
            when {
                binding.etEmail.text.isNullOrEmpty() -> {
                    binding.etEmail.error = "Email must not be null"
                }
                binding.etPassword.text.isNullOrEmpty() -> {
                    binding.etPassword.error = "Password must not be null"
                }
                else -> login()
            }
        }
    }

    private fun login() {
        authViewModel
            .login(binding.etEmail.text.toString(), binding.etPassword.text.toString())
            .observe(this, Observer {

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

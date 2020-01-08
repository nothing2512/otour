package com.example.otour.ui.setting


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.otour.R
import com.example.otour.databinding.FragmentSettingBinding
import com.example.otour.utils.getBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingFragment : Fragment() {

    private lateinit var binding: FragmentSettingBinding
    private val settingViewModel: SettingViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getBinding(R.layout.fragment_setting, container)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        settingViewModel.user.observe(this, Observer {
            settingViewModel.saveUser(it.data?.data)
        })
    }

    companion object {

        @JvmStatic
        fun newInstance() = SettingFragment()
    }
}

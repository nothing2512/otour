package com.example.otour.ui.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.otour.R
import com.example.otour.databinding.FragmentHomeBinding
import com.example.otour.utils.getBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getBinding(R.layout.fragment_home, container)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        homeViewModel.getPromo().observe(this, Observer {

        })

        homeViewModel.getBest().observe(this, Observer {

        })

        homeViewModel.getPopular().observe(this, Observer {

        })
    }

    companion object {

        @JvmStatic
        fun newInstance() = HomeFragment()
    }


}

package com.example.tatafood.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import com.example.tatafood.Adapters.ViewPagerAdapter
import com.example.tatafood.R
import com.example.tatafood.databinding.FragmentRegisterBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    lateinit var binding:FragmentRegisterBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding= FragmentRegisterBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewPager(childFragmentManager,lifecycle)
    }

    private fun setUpViewPager(fragmentManager: FragmentManager, lifecycle: Lifecycle) {
        binding.apply {
            val adapter = ViewPagerAdapter(fragmentManager, lifecycle)
           viewPager.adapter = adapter
            TabLayoutMediator(tabLayout,viewPager) { tab, position ->
                when (position) {
                    0 -> tab.text = "Login"
                    1 -> tab.text = "Sign-up"
                }
            }.attach()
        }
    }

}
package com.example.tatafood.ui.auth

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.tatafood.R
import com.example.tatafood.databinding.FragmentSplashBinding
import com.example.tatafood.ui.home.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    lateinit var binding: FragmentSplashBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding= FragmentSplashBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//            Handler(Looper.getMainLooper()).postDelayed({
//        Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_registerFragment)
//        removeSplashFragment()
//            },2000)
        binding.lottiAnim.addAnimatorListener(object :Animator.AnimatorListener{
            override fun onAnimationStart(p0: Animator) {}

            override fun onAnimationEnd(p0: Animator) {
               setUpAuth()
            }

            override fun onAnimationCancel(p0: Animator) {}

            override fun onAnimationRepeat(p0: Animator) {}
        })
    }

//    fun removeSplashFragment(){
//        parentFragmentManager.commit {
//            remove(this@SplashFragment)
//        }
//    }
    private fun setUpAuth() {
        auth = Firebase.auth
        if (auth.currentUser != null) {
            var intent=Intent(context,MainActivity::class.java)
            startActivity(intent)
        } else {
            findNavController().navigate(R.id.action_splashFragment_to_registerFragment)
        }

    }
}
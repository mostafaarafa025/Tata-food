package com.example.tatafood.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.tatafood.R
import com.example.tatafood.databinding.FragmentSignupBinding
import com.example.tatafood.ui.home.MainActivity
import com.example.tatafood.viewModels.HomeViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupFragment : Fragment() {
    lateinit var binding:FragmentSignupBinding
    lateinit var auth:FirebaseAuth
    private val homeViewModel: HomeViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
     binding= FragmentSignupBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth= Firebase.auth
    binding.apply {
        signUpBtn.setOnClickListener {
            val email = editTextEmail.editText?.text.toString().trim()
            val name = editTextUserName.editText?.text.toString().trim()
            val psw = editTextPassword.editText?.text.toString().trim()
            val confPass=editTextPasswordRematch.editText?.text.toString().trim()
        if (email.isNotEmpty() &&name.isNotEmpty()&&psw.isNotEmpty()&&confPass.isNotEmpty()){
            if (psw==confPass){
            auth.createUserWithEmailAndPassword(email,psw)
                .addOnCompleteListener { task ->
        if (task.isSuccessful){
//            homeViewModel.saveUserName(name)
        val intent=Intent(context,MainActivity::class.java)
            startActivity(intent)
        }else{
            Toast.makeText(context,task.exception.toString(),Toast.LENGTH_SHORT).show()
        }
            }
            }else{
                Toast.makeText(context,"password is not match",Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(context,"Empty fields are not allowed",Toast.LENGTH_SHORT).show()
        }
        }
    }
    }
}
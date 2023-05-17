package com.example.tatafood.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.tatafood.R
import com.example.tatafood.databinding.FragmentLoginBinding
import com.example.tatafood.ui.home.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    lateinit var binding:FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth= Firebase.auth
        binding.apply {
            loginBtn.setOnClickListener {
                val email = editTextEmail.editText?.text.toString()
                val password = editTextPassword.editText?.text.toString()
                if (!password.isEmpty() && !email.isEmpty()) {
                    auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                              var intent=Intent(context,MainActivity::class.java)
                                startActivity(intent)
                                activity?.finish()
                            } else {
                                Toast.makeText(
                                    context,
                                    task.exception!!.message,
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            }
                        }
                } else
                    Toast.makeText(
                        context,
                        "Sorry you can not enter empty fields",
                        Toast.LENGTH_SHORT
                    )
                        .show()


            }
        }
    }

}
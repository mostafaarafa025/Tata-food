package com.example.tatafood.ui.home

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.fragment.app.viewModels
import com.example.tatafood.R
import com.example.tatafood.databinding.FragmentProfileBinding
import com.example.tatafood.ui.auth.AuthenticationActivity
import com.example.tatafood.viewModels.HomeViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    lateinit var binding:FragmentProfileBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var dataStore: DataStore<Preferences>
    private val homeViewModel: HomeViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
     binding= FragmentProfileBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth=Firebase.auth
    showUserData()
        binding.btnLogout.setOnClickListener {
            showDialog()
        }
    }

    private fun showUserData() {
    binding.tvEmailProfile.text=auth.currentUser?.email
     //  binding.tvNameProfile.text= homeViewModel.getUserName()
    }

    fun showDialog() {
        val builder = AlertDialog.Builder(activity)
        builder.setMessage("Are u sure want Logout?")
            .setTitle("")
            .setPositiveButton("Yes") { dialog, id ->
                auth.signOut()
                startActivity(Intent(activity, AuthenticationActivity::class.java))
                activity?.finish()
            }
            .setNegativeButton("Cancel") { dialog, id ->
                // User clicked No button
            }

        val dialog = builder.create()
        dialog.show()
    }

}
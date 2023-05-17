package com.example.tatafood.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide

import com.example.tatafood.R
import com.example.tatafood.databinding.FragmentDetailsBinding

import com.example.tatafood.viewModels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    lateinit var binding:FragmentDetailsBinding
    private val args: DetailsFragmentArgs by navArgs()
    private val homeViewModel: HomeViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mealId=args.mealId

        homeViewModel.getMealDetails(mealId)
        homeViewModel.getMealDetailsLiveData.observe(viewLifecycleOwner){data->
            Glide.with(this)
                .load(data.strMealThumb)
                .error(R.drawable.ic_wifi_broken)
                .placeholder(R.drawable.olives)
                .into(binding.mealIv)
            binding.mealName.text=data.strMeal
            binding.mealOrigin.text=data.strArea
            binding.mealTags.text=data.strTags
            binding.mealRecipe.text=data.strInstructions

        }

       }

    }


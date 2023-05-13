package com.example.tatafood.ui

import android.os.Binder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.tatafood.Adapters.MealAdapter
import com.example.tatafood.R
import com.example.tatafood.databinding.FragmentMealsBinding
import com.example.tatafood.databinding.MealsRowBinding
import com.example.tatafood.model.Meal
import com.example.tatafood.viewModels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealsFragment : Fragment() {
    private val homeViewModel: HomeViewModel by viewModels()
lateinit var binding:FragmentMealsBinding
    private val args: MealsFragmentArgs by navArgs()

    private lateinit var mealAdapter: MealAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mealAdapter=MealAdapter(homeViewModel)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding= FragmentMealsBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val categoryName = args.categoryName

        homeViewModel.getMeals(categoryName)

        homeViewModel.getMealLiveData.observe(viewLifecycleOwner){data->

            mealAdapter.differ.submitList(data)

            binding.mealsRv.apply {
                setHasFixedSize(true)
                layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                adapter = mealAdapter

            }
        }

        mealAdapter.onItemClickListener={
            val args=Bundle()
            args.putString("mealId", it.idMeal)
            findNavController().navigate(R.id.action_mealsFragment_to_detailsFragment,args)
        }
    }


}
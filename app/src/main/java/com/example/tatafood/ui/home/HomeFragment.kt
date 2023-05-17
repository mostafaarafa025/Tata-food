package com.example.tatafood.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.tatafood.Adapters.CategoryAdapter
import com.example.tatafood.Adapters.OverAdapter
import com.example.tatafood.R
import com.example.tatafood.databinding.FragmentHomeBinding
import com.example.tatafood.viewModels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val homeViewModel:HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var overAdapter:OverAdapter
    private lateinit var categoryAdapter:CategoryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overAdapter=OverAdapter()
        categoryAdapter= CategoryAdapter()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       getRandomMeal()
        getOverMeals()
        setupOverRecyclerView()
        getCategorymeal()
        setUpCategoryRecycler()

        categoryAdapter.onItemClickListener={
            val args=Bundle()
            args.putString("categoryName", it.strCategory)
            findNavController().navigate(R.id.action_homeFragment_to_mealsFragment,args)
        }
        overAdapter.onItemClickListener={
            val args=Bundle()
            args.putString("mealId",it.idMeal)
            findNavController().navigate(R.id.action_homeFragment_to_detailsFragment,args)
        }

        }

    private fun setUpCategoryRecycler() {
        binding.categoriesRv.apply {
            adapter=categoryAdapter
        }
    }

    private fun getCategorymeal() {
       homeViewModel.getCategory()
        homeViewModel.getCategoryLiveData.observe(viewLifecycleOwner){data->
            categoryAdapter.differ.submitList(data)
        }
    }
    private fun getOverMeals() {
       homeViewModel.getOverMeal()
        homeViewModel.getOverMealLiveData.observe(viewLifecycleOwner){data->
        overAdapter.differ.submitList(data)
        }
    }
    private fun setupOverRecyclerView() {
        binding.popularRv.apply {
            adapter=overAdapter
        }
    }
    private fun getRandomMeal() {
        homeViewModel.getRandomMeal()
        homeViewModel.getRandomLiveData.observe(viewLifecycleOwner){data->
            Glide.with(this)
                .load(data.strMealThumb)
                .into(binding.randomImage)
    }
}
}
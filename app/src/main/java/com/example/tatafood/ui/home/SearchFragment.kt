package com.example.tatafood.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.tatafood.Adapters.MealAdapter
import com.example.tatafood.R

import com.example.tatafood.databinding.FragmentSearchBinding
import com.example.tatafood.viewModels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {
  private lateinit var binding: FragmentSearchBinding
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var searchAdapter: MealAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding= FragmentSearchBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

       setUpRecyclerView()
        getMealsFromSearch()

        searchAdapter.onItemClickListener={
            val args= Bundle()
            args.putString("mealId", it.idMeal)
            findNavController().navigate(R.id.action_searchFragment_to_detailsFragment,args)
        }
    }

    private fun setUpRecyclerView(){
        searchAdapter= MealAdapter(homeViewModel)
        binding.searchRv.apply {
    layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter=searchAdapter
        }
    }

    private fun getMealsFromSearch(){
        var job:Job?=null
        binding.searchTv.addTextChangedListener { editable ->
            job?.cancel()
            job= MainScope().launch {
            showProgressBar()
            editable.let {
        if (editable.toString().isNotEmpty()){
            delay(1000)
            homeViewModel.searchData(editable.toString())
            hideProgressBar()
        }else if (editable.toString().isEmpty()){
        hideProgressBar()
            searchAdapter.differ.submitList(null)
        hideNoResults()
        }
            }
            }
        }
            homeViewModel.searchData.observe(viewLifecycleOwner, Observer { data ->
                      if (data!=null){
                    hideNoResults()
                        searchAdapter.differ.submitList(data)
                    }else {
                        showNoResults()
                searchAdapter.differ.submitList(null)
                    }
            })

    }

    private fun hideProgressBar() {
       binding.ProgressBar.visibility = View.INVISIBLE
    }
    private fun showProgressBar() {
        binding.ProgressBar.visibility = View.VISIBLE
    }
    private fun showNoResults(){
        binding.noResultsImg.visibility = View.VISIBLE

    }
    private fun hideNoResults(){
        binding.noResultsImg.visibility = View.INVISIBLE
    }



}
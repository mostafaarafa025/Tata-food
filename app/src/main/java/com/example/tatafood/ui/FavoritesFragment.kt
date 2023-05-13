package com.example.tatafood.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.tatafood.Adapters.FavoritesAdapter
import com.example.tatafood.R
import com.example.tatafood.databinding.FragmentFavoritesBinding
import com.example.tatafood.viewModels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {
    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var favoritesAdapter: FavoritesAdapter

    private val homeViewModel: HomeViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentFavoritesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoritesAdapter= FavoritesAdapter(homeViewModel)
        binding.favouriteRv.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = favoritesAdapter
        }

        homeViewModel.favorites.observe(viewLifecycleOwner, Observer {
            if (it.isEmpty()) {
                binding.favouriteRv.visibility = View.GONE
                binding.favouriteEmptyList.visibility = View.VISIBLE
            } else {
                favoritesAdapter.differ.submitList(it)
                binding.favouriteEmptyList.visibility = View.GONE
                binding.favouriteRv.visibility = View.VISIBLE
            }
        })
        favoritesAdapter.onItemClickListener={
            val args=Bundle()
            args.putString("mealId",it.idMeal)
            findNavController().navigate(R.id.action_favoritesFragment_to_detailsFragment,args)
        }
    }


}
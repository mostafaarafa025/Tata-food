package com.example.tatafood.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tatafood.model.Category
import com.example.tatafood.model.MealDetails
import com.example.tatafood.model.Meal
import com.example.tatafood.network.repsoitory.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
):ViewModel()  {

    var favorites: LiveData<List<Meal>>

    init {
        favorites = homeRepository.getFavourite()
        getCategory()
    }

    private val _getRandomLiveData=MutableLiveData<MealDetails>()
        val getRandomLiveData:LiveData<MealDetails> = _getRandomLiveData

        fun getRandomMeal()=viewModelScope.launch {
            val response=homeRepository.getRandomMeal()
           try {
               if(response.isSuccessful){
                   response.body()!!.meals.let {
                       _getRandomLiveData.postValue(it[0])
                       Log.e("Great request from random meal", "getData: Great")
                   }
               }
               else Log.e("Failed request from random meal ", response.errorBody().toString())
           }
           catch (ex: Exception) {
               Log.e("TAG", ex.message.toString()+"Error random meal")
           }
            }


    private val _getOverMealLiveData=MutableLiveData<List<Meal>>()
    val getOverMealLiveData:LiveData<List<Meal>> = _getOverMealLiveData

    fun getOverMeal()=viewModelScope.launch {
       try {
           val response=homeRepository.getOverMeal("Seafood")
           if(response.isSuccessful){
               response.body()!!.meals.let {
                   _getOverMealLiveData.postValue(it)
                   Log.e("Great request  from over meal", "getData: Great")
               }
           }
           else Log.e("Failed request from over meal", response.errorBody().toString())
       }
       catch (ex: Exception) {
           Log.e("TAG", ex.message.toString()+"Error over data meal")
       }
    }


    private val _getCategoriesLiveData=MutableLiveData<List<Category>>()
    val getCategoryLiveData:LiveData<List<Category>> = _getCategoriesLiveData

        fun getCategory()=viewModelScope.launch {
           try {
               val response = homeRepository.getCategories()
               if (response.isSuccessful) {
                   response.body()!!.categories.let {
                       _getCategoriesLiveData.postValue(it)
                       Log.e("Great request  from category", "getData: Great")
                   }
               }
               else  Log.e("Failed request from category", response.errorBody().toString())
           }
           catch (ex: Exception) {
               Log.e("TAG", ex.message.toString()+"Error category")
           }
        }
    private val _getMealLiveData=MutableLiveData<List<Meal>>()
    val getMealLiveData:LiveData<List<Meal>> = _getMealLiveData

    fun getMeals(category: String)=viewModelScope.launch {
        try {
            val response=homeRepository.getMeals(category)
            if(response.isSuccessful){
                response.body()!!.meals.let {
                    _getMealLiveData.postValue(it)
                    Log.e("Great request  from get meal", "getData: Great")
                }
            }
            else Log.e("Failed request from get meal", response.errorBody().toString())
        }
        catch (ex: Exception) {
            Log.e("TAG", ex.message.toString()+"Error get data meal")
        }
    }
    private val _getMealDetailsLiveData=MutableLiveData<MealDetails>()
    val getMealDetailsLiveData:LiveData<MealDetails> = _getMealDetailsLiveData

    fun getMealDetails(id:String)=viewModelScope.launch {
        try {
            val response=homeRepository.getMealDetails(id)
            if(response.isSuccessful){
                response.body()!!.meals.let {
                    _getMealDetailsLiveData.postValue(it[0])
                    Log.e("Great request  from get meal details", "getData: Great")
                }
            }
            else Log.e("Failed request from get meal details", response.errorBody().toString())
        }
        catch (ex: Exception) {
            Log.e("TAG", ex.message.toString()+"Error get data meal details")
        }
    }


    private val _searchData=MutableLiveData<List<Meal>>()
    val searchData:LiveData<List<Meal>> = _searchData

    fun searchData(meal:String)=viewModelScope.launch {
        try {
            val response=homeRepository.searchForMeal(meal)
            if (response.isSuccessful){
                response.body()!!.meals.let {
                    _searchData.postValue(it)
                    Log.e("Great request  from search meal ", "getData: Great")
                }
            }
        }catch (ex: Exception) {
            Log.e("TAG", ex.message.toString()+"Error get data search meal ")
        }
    }

    fun addFavourite(meal: Meal) = viewModelScope.launch {
        homeRepository.addFavourite(meal) }

    fun removeFavourite(meal: Meal) = viewModelScope.launch {
        homeRepository.removeFavourite(meal) }
//    fun saveUserName(value: String) {
//        viewModelScope.launch {
//            homeRepository.saveUserName("userName", value)
//        }
//    }
//
//    fun getUserName(): String? = runBlocking {
//        homeRepository.getUserName("userName")
//    }


    // fun getFavorites() =homeRepository.getFavoritesMeal
}


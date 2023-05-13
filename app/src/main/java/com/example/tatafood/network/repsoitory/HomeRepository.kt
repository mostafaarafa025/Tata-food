package com.example.tatafood.network.repsoitory

import com.example.tatafood.model.Meal
import com.example.tatafood.network.remote.MealApi
import com.example.tatafood.network.local.MealsDatabase
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val mealApi: MealApi,
    private val mealsDatabase: MealsDatabase
){
private val dataBase=mealsDatabase.mealsDao()


    suspend fun getRandomMeal()=mealApi.getRandomMeal()

    suspend fun getOverMeal(categoryName:String)=mealApi.getOverMeals(categoryName)

    suspend fun getCategories()=mealApi.getCategories()

    suspend fun getMeals(category: String) = mealApi.getMeals(category)

    suspend fun getMealDetails(id:String)=mealApi.getMealDetails(id)

    suspend fun searchForMeal(meal: String) = mealApi.searchForMeal(meal)

    suspend fun addFavourite(meal: Meal) = dataBase.upsertMeal(meal)

    suspend fun removeFavourite(meal: Meal) = dataBase.delete(meal)

    fun getFavourite() = dataBase.getFavMeals()

  //  val getFavoritesMeal=dataBase.getFavMeals()
}
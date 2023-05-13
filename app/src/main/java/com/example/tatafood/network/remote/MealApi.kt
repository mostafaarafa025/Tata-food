package com.example.tatafood.network.remote

import com.example.tatafood.model.CategoryResponse
import com.example.tatafood.model.MealDetailsResponse
import com.example.tatafood.model.MealResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {

    @GET("random.php")
    suspend fun getRandomMeal():Response<MealDetailsResponse>

    @GET("filter.php")
    suspend fun getOverMeals(
        @Query("c") categoryName:String
    ):Response<MealResponse>

    @GET("categories.php")
    suspend fun getCategories():Response<CategoryResponse>

    @GET("filter.php")
    suspend fun getMeals(
        @Query("c") category: String
    ): Response<MealResponse>

    @GET("lookup.php")
    suspend fun getMealDetails(
        @Query("i") id: String
    ):Response<MealDetailsResponse>


    @GET("search.php")
    suspend fun searchForMeal(
        @Query("s") meal: String
    ): Response<MealResponse>

}
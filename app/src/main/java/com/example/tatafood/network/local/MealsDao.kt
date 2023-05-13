package com.example.tatafood.network.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.tatafood.model.Meal

@Dao
interface MealsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertMeal(meal:Meal):Long

    @Query("SELECT * FROM meals_table ")
    fun getFavMeals(): LiveData<List<Meal>>

    @Delete
    suspend fun delete(meal: Meal)
}
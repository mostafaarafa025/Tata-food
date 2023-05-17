package com.example.tatafood.network.repsoitory

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.tatafood.model.Meal
import com.example.tatafood.network.remote.MealApi
import com.example.tatafood.network.local.MealsDatabase
import dagger.hilt.android.internal.Contexts
import kotlinx.coroutines.flow.first
import javax.inject.Inject
private val Context.dataStore: DataStore<Preferences>
        by preferencesDataStore(name = "profile_data")
class HomeRepository @Inject constructor(

    private val mealApi: MealApi,
    private val mealsDatabase: MealsDatabase


){
private val dataBase=mealsDatabase.mealsDao()

//    suspend fun saveUserName(key: String, value: String) {
//        val preferencesKey = stringPreferencesKey(key)
//        context.dataStore.edit { userDataPreferences ->
//            userDataPreferences[preferencesKey] = value
//        }
//    }
//
//    suspend fun getUserName(key: String): String? {
//        return try {
//            val preferencesKey = stringPreferencesKey(key)
//            val userDataPreferences = context.dataStore.data.first()
//            userDataPreferences[preferencesKey]
//        } catch (e: Exception) {
//            e.printStackTrace()
//            null
//        }
//    }
    suspend fun getRandomMeal()=mealApi.getRandomMeal()

    suspend fun getOverMeal(categoryName:String)=mealApi.getOverMeals(categoryName)

    suspend fun getCategories()=mealApi.getCategories()

    suspend fun getMeals(category: String) = mealApi.getMeals(category)

    suspend fun getMealDetails(id:String)=mealApi.getMealDetails(id)

    suspend fun searchForMeal(meal: String) = mealApi.searchForMeal(meal)

    suspend fun addFavourite(meal: Meal) = dataBase.upsertMeal(meal)

    suspend fun removeFavourite(meal: Meal) = dataBase.delete(meal)

    fun getFavourite() = dataBase.getFavMeals()


}
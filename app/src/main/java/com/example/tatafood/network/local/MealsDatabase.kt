package com.example.tatafood.network.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tatafood.model.Meal
import com.example.tatafood.network.local.MealsDao

@Database(entities = [Meal::class], version = 1)
abstract class MealsDatabase :RoomDatabase(){
    abstract fun mealsDao():MealsDao

}
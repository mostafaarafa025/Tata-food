package com.example.tatafood.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.tatafood.network.remote.MealApi
import com.example.tatafood.network.local.MealsDatabase
import com.example.tatafood.utils.Constants
import com.example.tatafood.utils.Constants.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

        @Provides
        @Singleton
    fun provideApi(): MealApi =
        Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MealApi::class.java)

    @Provides
    @Singleton
    fun provideDatabase(application: Application): MealsDatabase =
        Room.databaseBuilder(
            application,
            MealsDatabase::class.java,
            "meals_database")
            .build()



}
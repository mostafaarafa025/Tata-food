package com.example.tatafood.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.RoomMasterTable.TABLE_NAME
import com.example.tatafood.utils.Constants

@Entity(tableName = "meals_table")
data class Meal(
    @PrimaryKey(autoGenerate = false)
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String,
    var isFavorite: Boolean = false
)
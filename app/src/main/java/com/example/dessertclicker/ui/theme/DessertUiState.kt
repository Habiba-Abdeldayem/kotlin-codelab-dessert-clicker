package com.example.dessertclicker.ui.theme

import com.example.dessertclicker.R

data class DessertUiState (
    var revenue:Int = 0,
    var dessertsSold:Int = 0,
    val currentDessertIndex:Int = 0,
    var currentDessertPrice:Int = 0,
    var currentDessertImageId:Int = R.drawable.cupcake
)
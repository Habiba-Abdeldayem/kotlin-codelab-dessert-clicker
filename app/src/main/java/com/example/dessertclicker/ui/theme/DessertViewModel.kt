package com.example.dessertclicker.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource.allDesserts
import com.example.dessertclicker.model.Dessert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertViewModel:ViewModel() {
    private val _uiState = MutableStateFlow(DessertUiState())
    val uiState : StateFlow<DessertUiState> = _uiState.asStateFlow()

   private fun determineDessertToShow(
        desserts: List<Dessert> = allDesserts,
        dessertsSold: Int = _uiState.value.dessertsSold
    ): Dessert {
        var dessertToShow = desserts.first()
        for (dessert in desserts) {
            if (dessertsSold >= dessert.startProductionAmount) {
                dessertToShow = dessert
            } else {
                // The list of desserts is sorted by startProductionAmount. As you sell more desserts,
                // you'll start producing more expensive desserts as determined by startProductionAmount
                // We know to break as soon as we see a dessert who's "startProductionAmount" is greater
                // than the amount sold.
                break
            }
        }

        return dessertToShow
    }
    fun updateRevenue(){
        _uiState.update { currentState -> currentState.copy(
            revenue = _uiState.value.revenue + _uiState.value.currentDessertPrice,
            dessertsSold =  _uiState.value.dessertsSold + 1

        ) }
    }

    fun showNextDessert(){
        val dessertToShow = determineDessertToShow()
        _uiState.update { currentState -> currentState.copy(
            currentDessertImageId = dessertToShow.imageId,
            currentDessertPrice = dessertToShow.price
        ) }
    }
    /**
     * Determine which dessert to show.
     */


}
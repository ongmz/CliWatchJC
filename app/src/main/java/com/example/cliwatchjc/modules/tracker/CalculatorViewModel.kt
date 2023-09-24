package com.example.cliwatchjc.modules.tracker

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class CalculatorViewModel : ViewModel() {
    private val _transportation = MutableStateFlow(0.0f)
    private val transportation: StateFlow<Float> = _transportation.asStateFlow()

    private val _energyUsage = MutableStateFlow(0.0f)
    private val energyUsage: StateFlow<Float> = _energyUsage.asStateFlow()

    private val _waste = MutableStateFlow(0.0f)
    private val waste: StateFlow<Float> = _waste.asStateFlow()

    private val _carbonFootprint = MutableStateFlow(0.0f)
    val carbonFootprint: StateFlow<Float> = _carbonFootprint.asStateFlow()

    // Function to calculate the carbon footprint
    fun calculateCarbonFootprint() {
        val transportationValue = transportation.value
        val energyUsageValue = energyUsage.value
        val wasteValue = waste.value

        val calculatedFootprint = transportationValue + energyUsageValue + wasteValue

        _carbonFootprint.value = calculatedFootprint
    }

    // Function to set the transportation input
    fun setTransportation(value: Float) {
        _transportation.value = value
    }

    // Function to set the energy usage input
    fun setEnergyUsage(value: Float) {
        _energyUsage.value = value
    }

    // Function to set the waste input
    fun setWaste(value: Float) {
        _waste.value = value
    }

    // Function to reset all inputs and the calculated carbon footprint
    fun resetInputs() {
        _transportation.value = 0.0f
        _energyUsage.value = 0.0f
        _waste.value = 0.0f
        _carbonFootprint.value = 0.0f
    }
}


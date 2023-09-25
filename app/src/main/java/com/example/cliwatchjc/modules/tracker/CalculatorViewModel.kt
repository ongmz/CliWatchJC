package com.example.cliwatchjc.modules.tracker

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor() : ViewModel() {
    private val _transportation = MutableStateFlow(0.0f)
    private val transportation: StateFlow<Float> = _transportation.asStateFlow()

    private val _energyUsage = MutableStateFlow(0.0f)
    private val energyUsage: StateFlow<Float> = _energyUsage.asStateFlow()

    private val _waste = MutableStateFlow(0.0f)
    private val waste: StateFlow<Float> = _waste.asStateFlow()

    private val _carbonFootprint = MutableStateFlow(0.0f)
    val carbonFootprint: StateFlow<Float> = _carbonFootprint.asStateFlow()

    // Weekly data properties
    private val _weeklyTransportationData = MutableStateFlow<List<Float>>(emptyList())
    val weeklyTransportationData: StateFlow<List<Float>> = _weeklyTransportationData.asStateFlow()

    private val _weeklyEnergyUsageData = MutableStateFlow<List<Float>>(emptyList())
    val weeklyEnergyUsageData: StateFlow<List<Float>> = _weeklyEnergyUsageData.asStateFlow()

    private val _weeklyWasteData = MutableStateFlow<List<Float>>(emptyList())
    val weeklyWasteData: StateFlow<List<Float>> = _weeklyWasteData.asStateFlow()

    // Monthly data properties
    private val _monthlyTransportationData = MutableStateFlow<List<Float>>(emptyList())
    val monthlyTransportationData: StateFlow<List<Float>> = _monthlyTransportationData.asStateFlow()

    private val _monthlyEnergyUsageData = MutableStateFlow<List<Float>>(emptyList())
    val monthlyEnergyUsageData: StateFlow<List<Float>> = _monthlyEnergyUsageData.asStateFlow()

    private val _monthlyWasteData = MutableStateFlow<List<Float>>(emptyList())
    val monthlyWasteData: StateFlow<List<Float>> = _monthlyWasteData.asStateFlow()

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


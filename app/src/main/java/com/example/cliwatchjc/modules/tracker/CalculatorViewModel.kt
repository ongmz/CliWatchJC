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

    fun generateSampleWeeklyTransportationData(): List<Float> {
        return listOf(10.0f, 15.0f, 12.0f, 8.0f, 20.0f, 18.0f, 13.0f)
    }

    fun generateSampleMonthlyTransportationData(): List<Float> {
        return listOf(50.0f, 55.0f, 42.0f, 38.0f, 60.0f, 58.0f, 53.0f)
    }

    fun generateSampleWeeklyEnergyUsageData(): List<Float> {
        return listOf(30.0f, 25.0f, 32.0f, 28.0f, 35.0f, 40.0f, 37.0f)
    }

    fun generateSampleMonthlyEnergyUsageData(): List<Float> {
        return listOf(150.0f, 155.0f, 142.0f, 138.0f, 160.0f, 158.0f, 153.0f)
    }

    fun generateSampleWeeklyWasteData(): List<Float> {
        return listOf(5.0f, 8.0f, 6.0f, 7.0f, 9.0f, 5.0f, 4.0f)
    }

    fun generateSampleMonthlyWasteData(): List<Float> {
        return listOf(20.0f, 28.0f, 22.0f, 26.0f, 30.0f, 25.0f, 24.0f)
    }
}


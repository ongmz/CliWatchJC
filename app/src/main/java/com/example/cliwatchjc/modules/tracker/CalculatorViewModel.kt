package com.example.cliwatchjc.modules.tracker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor() : ViewModel() {
    private val _transportation = MutableStateFlow(0.0f)
    val transportation: StateFlow<Float> = _transportation.asStateFlow()

    private val _energyUsage = MutableStateFlow(0.0f)
    val energyUsage: StateFlow<Float> = _energyUsage.asStateFlow()

    private val _food = MutableStateFlow(0.0f)
    val food: StateFlow<Float> = _food.asStateFlow()

    private val _carbonFootprint = MutableStateFlow(0.0f)
    val carbonFootprint: StateFlow<Float> = _carbonFootprint.asStateFlow()

    private val _calculatorResultNavigation = MutableStateFlow<CalculatorScreenNavigation?>(null)
    val calculatorResultNavigation: StateFlow<CalculatorScreenNavigation?> = _calculatorResultNavigation

    sealed class CalculatorScreenNavigation {
        data object CalculatorResult : CalculatorScreenNavigation()
    }

    fun navigateToResultScreen(navController: NavHostController) {
        viewModelScope.launch {
            _calculatorResultNavigation.value = CalculatorScreenNavigation.CalculatorResult
        }
    }

    fun setSampleData() {
        // Sample data
        val transportationDistance = 100.0f // 100 km traveled
        val energyElectricity = 500.0f // 500 kWh electricity
        val energyNaturalGas = 500.0f // 500 MJ natural gas
        val energyFirewood = 100.0f // 100 kg firewood
        val foodSpending = 70.0f // $70 spent on food per week

        // Calculate carbon footprint based on sample data
        val maxTransportationCO2e = calculateTransportation("Car", transportationDistance.toString())
        val maxEnergyCO2e = calculateEnergy(energyElectricity.toString(), energyNaturalGas.toString(), energyFirewood.toString())
        val maxFoodCO2e = calculateFood("Meat Lover", foodSpending.toString())

        // Calculate total carbon footprint based on sample data
        val totalMaxCO2e = maxTransportationCO2e + maxEnergyCO2e + maxFoodCO2e

        // Calculate percentages within 0-100% inclusive range
        val transportationPercentage = (maxTransportationCO2e / totalMaxCO2e) * 100
        val energyPercentage = (maxEnergyCO2e / totalMaxCO2e) * 100
        val foodPercentage = (maxFoodCO2e / totalMaxCO2e) * 100

        // Ensure percentages add up to 100%
        val totalPercentage = transportationPercentage + energyPercentage + foodPercentage

        if (totalPercentage < 100f) {
            // If total percentage is less than 100%, distribute the remaining percentage equally
            val remainingPercentage = 100f - totalPercentage
            val equalDistribution = remainingPercentage / 3
            _transportation.value = (transportationPercentage + equalDistribution).coerceIn(0f, 100f)
            _energyUsage.value = (energyPercentage + equalDistribution).coerceIn(0f, 100f)
            _food.value = (foodPercentage + equalDistribution).coerceIn(0f, 100f)
        } else {
            // If total percentage is more than 100%, reduce percentages proportionally
            _transportation.value = transportationPercentage.coerceIn(0f, 100f)
            _energyUsage.value = energyPercentage.coerceIn(0f, 100f)
            _food.value = foodPercentage.coerceIn(0f, 100f)
        }

        // Set total carbon footprint
        _carbonFootprint.value = totalMaxCO2e
    }

    fun setCarbonFootprint(value: Float) {
        _carbonFootprint.value = value
    }

    fun calculateTransportation(transportation: String, distance: String): Float {
        val distanceInKm = distance.toFloatOrNull() ?: 0.0f
        val transportationCO2ePerKm = when (transportation) {
            "Car" -> 0.1f
            "Motorcycle" -> 0.09f
            "Public Transport" -> 0.25f
            else -> 0.0f
        }
        return distanceInKm * transportationCO2ePerKm
    }

    fun calculateFood(diet: String, spending: String): Float {
        val spendingPerWeek = spending.toFloatOrNull() ?: 0.0f
        val foodCO2ePerDollar = when (diet) {
            "Vegan" -> 0.03f
            "Vegetarian" -> 0.04f
            "Meat Lover" -> 0.05f
            else -> 0.0f
        }
        return spendingPerWeek * foodCO2ePerDollar
    }

    fun calculateEnergy(electricity: String, naturalGas: String, firewood: String): Float {
        val electricityUsageInKWh = electricity.toFloatOrNull() ?: 0.0f
        val naturalGasUsageInMJ = naturalGas.toFloatOrNull() ?: 0.0f
        val firewoodUsageInKg = firewood.toFloatOrNull() ?: 0.0f

        val electricityCO2ePerKWh = 0.03f
        val naturalGasCO2ePerMJ = 0.15f
        val firewoodCO2ePerKg = 0.19f

        val electricityCO2e = electricityUsageInKWh * electricityCO2ePerKWh
        val naturalGasCO2e = naturalGasUsageInMJ * naturalGasCO2ePerMJ
        val firewoodCO2e = firewoodUsageInKg * firewoodCO2ePerKg

        return electricityCO2e + naturalGasCO2e + firewoodCO2e
    }

}


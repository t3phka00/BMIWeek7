package com.example.bmicalculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlin.math.round

class BmiViewModel : ViewModel() {
    val bmiLiveData = MutableLiveData<Float>()
    var height by mutableStateOf("")
    var weight by mutableStateOf("")

    fun calculateBmi() {
        viewModelScope.launch {
            val heightValue = height.toFloatOrNull() ?: 0.0f
            val weightValue = weight.toFloatOrNull() ?: 0.0f

            val bmi = if (heightValue > 0 && weightValue > 0) {
                round(weightValue / (heightValue * heightValue) * 10) / 10
            } else {
                0.0f
            }
            bmiLiveData.value = bmi
        }
    }
}
package com.example.cliwatchjc.modules.education

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.cliwatchjc.Routes

@Composable
fun ClimateNewsScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = Routes.labels[Routes.CLIMATE_NEWS] ?: "")
    }
}
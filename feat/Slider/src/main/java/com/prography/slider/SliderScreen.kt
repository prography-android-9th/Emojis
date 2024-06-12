package com.prography.slider

import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Parcelize
data object SliderScreen : Screen {
    data class State(
        val currentDate: String,
        val emojiList: List<SliderItem>
    ): CircuitUiState
}


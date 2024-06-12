package com.example.diary

import com.example.diary.model.Diary
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import kotlinx.parcelize.Parcelize

@Parcelize
data class DiaryScreen(val id: String) : Screen {
    data class State(val diary: Diary, val eventSink: (Event) -> Unit) : CircuitUiState
    sealed interface Event : CircuitUiEvent {
        data object BackClicked : Event
        data object WriteButtonClicked : Event
        data object dateButtonClicked: Event
        data object submitButtonClicked: Event
    }
}
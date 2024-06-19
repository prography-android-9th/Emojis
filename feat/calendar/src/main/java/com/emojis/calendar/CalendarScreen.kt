package com.emojis.calendar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
@OptIn(ExperimentalFoundationApi::class)
data class CalendarScreen(val id: String) : Screen {
    data class State(
        val currentMonth: LocalDate,
        val selectedDate: LocalDate,
        val pagerState: PagerState,
        val currentPage: Int,
        val itemList: List<CalendarItem>,
        val eventSink: (Event) -> Unit
    ) : CircuitUiState

    sealed interface Event : CircuitUiEvent {
        data class DateSelected(val date: LocalDate) : Event
        data class PageChanged(val page: Int) : Event
        data object AddButtonClicked : Event
    }
}
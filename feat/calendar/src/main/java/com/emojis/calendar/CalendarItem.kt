package com.emojis.calendar

import androidx.compose.runtime.Immutable
import java.time.LocalDate

@Immutable
data class CalendarItem(
    val id: Int,
    val date: LocalDate,
    val emojis: List<String>
)
package com.emojis.calendar

import java.time.LocalDate

data class CalendarEvent(
    val date: LocalDate,
    val emojis: List<String>
)

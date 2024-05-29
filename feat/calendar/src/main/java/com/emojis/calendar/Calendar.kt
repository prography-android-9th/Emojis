package com.emojis.calendar

import androidx.compose.runtime.Immutable
import java.time.LocalDate

@Immutable
data class Calendar(
    val date: LocalDate,
    val emojis: List<String>
)

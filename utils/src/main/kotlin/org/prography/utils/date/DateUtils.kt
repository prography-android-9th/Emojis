package org.prography.utils.date

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.number
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

/**
 * Created by MyeongKi.
 */
object DateUtils {
    fun getCurrentLocalDateTime(): LocalDateTime {
        return getCurrentLocalDate().toLocalDateTime()
    }

    fun getCurrentLocalDate(): LocalDate {
        return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
    }

    fun getCurrentDateTime(): Long {
        return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).toTime()
    }
}

fun LocalDate.toTime(): Long {
    return LocalDateTime(date = this, time = LocalTime(0, 0)).toInstant(TimeZone.currentSystemDefault())
        .toEpochMilliseconds()
}

fun LocalDate.toLocalDateTime(): LocalDateTime {
    return LocalDateTime(date = this, time = LocalTime(0, 0))
}

fun LocalDateTime.toTime(): Long {
    return toInstant(TimeZone.currentSystemDefault()).toEpochMilliseconds()
}

fun Long.toLocalDateTime(): LocalDateTime {
    val instant = Instant.fromEpochMilliseconds(this)
    return instant.toLocalDateTime(TimeZone.currentSystemDefault())
}


fun LocalDateTime.toDisplayYMDText(): String {
    return "${date.year}.${date.month.number.toDoubleDigit()}.${date.dayOfMonth.toDoubleDigit()}"
}
fun LocalDateTime.toDisplayYMDTText(): String {
    return "${date.year}.${date.month.number.toDoubleDigit()}.${date.dayOfMonth.toDoubleDigit()} " + "${time.hour.toDoubleDigit()}:${time.minute.toDoubleDigit()}"
}

private fun Int.toDoubleDigit(): String {
    return if (this < 10) {
        "0$this"
    } else {
        this.toString()
    }
}

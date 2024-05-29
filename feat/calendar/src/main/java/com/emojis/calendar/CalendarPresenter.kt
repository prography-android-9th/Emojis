package com.emojis.calendar

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen
import java.time.LocalDate

class CalendarPresenter(
    private val screen: CalendarScreen,
    private val navigator: Navigator,

) : Presenter<CalendarScreen.State> {

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    override fun present(): CalendarScreen.State {

        val yearRange = IntRange(1970, 2100)

        var selectedDate by remember { mutableStateOf(LocalDate.now()) }
        val initialPage = (selectedDate.year - yearRange.first) * 12 + selectedDate.monthValue - 1

        var currentMonth by remember { mutableStateOf(selectedDate) }
        var currentPage by remember { mutableIntStateOf(initialPage) }

        val pageCount = (yearRange.last - yearRange.first) * 12
        val pagerState = rememberPagerState(initialPage = initialPage) { pageCount }

        return CalendarScreen.State(
            currentMonth = currentMonth,
            selectedDate = selectedDate,
            pagerState = pagerState,
            currentPage = currentPage,
            initialPage = initialPage
        ) { event ->
            when (event) {
                is CalendarScreen.Event.DateSelected -> {
                    selectedDate = event.date
                }

                is CalendarScreen.Event.PageChanged -> {
                    val addMonth = (event.page - currentPage).toLong()
                    currentMonth = currentMonth.plusMonths(addMonth)
                    currentPage = event.page
                }
            }
        }
    }

    class Factory() : Presenter.Factory {
        override fun create(
            screen: Screen,
            navigator: Navigator,
            context: CircuitContext,
        ): Presenter<*>? {
            return when (screen) {
                is CalendarScreen -> return CalendarPresenter(screen, navigator)
                else -> null
            }
        }
    }
}
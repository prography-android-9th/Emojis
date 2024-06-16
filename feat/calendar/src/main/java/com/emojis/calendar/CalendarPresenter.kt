package com.emojis.calendar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.emojis.calendar.DateConstants.YEAR_RANGE
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
        val (selectedDate, setSelectedDate) = remember {
            mutableStateOf(LocalDate.now())
        }

        val initialPage = (selectedDate.year - YEAR_RANGE.first) * 12 + selectedDate.monthValue - 1

        val (currentMonth, setCurrentMonth) = remember {
            mutableStateOf(selectedDate)
        }
        val (currentPage, setCurrentPage) = remember {
            mutableIntStateOf(initialPage)
        }
        val pagerState = rememberPagerState(initialPage = initialPage) {
            (YEAR_RANGE.last - YEAR_RANGE.first) * 12
        }

        return CalendarScreen.State(
            currentMonth = currentMonth,
            selectedDate = selectedDate,
            pagerState = pagerState,
            currentPage = currentPage
        ) { event ->
            when (event) {
                is CalendarScreen.Event.DateSelected -> {
                    setSelectedDate(event.date)
                }

                is CalendarScreen.Event.PageChanged -> {
                    val monthOffset = (event.page - currentPage).toLong()
                    setCurrentMonth(currentMonth.plusMonths(monthOffset))
                    setCurrentPage(event.page)
                }

                is CalendarScreen.Event.AddButtonClicked -> {

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
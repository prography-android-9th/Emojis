package com.emojis.calendar

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emojis.calendar.DateConstants.YEAR_RANGE
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CalendarUi(
    modifier: Modifier = Modifier,
    state: CalendarScreen.State
) {
    Column(modifier = modifier) {
        CalendarHeader(
            modifier = Modifier.padding(20.dp),
            text = state.currentMonth.format(
                DateTimeFormatter.ofPattern(
                    "MMMM yyyy",
                    Locale.ENGLISH
                )
            )
        )

        LaunchedEffect(state.pagerState.currentPage) {
            state.eventSink(CalendarScreen.Event.PageChanged(state.pagerState.currentPage))
        }

        HorizontalPager(state = state.pagerState) { page ->
            val date = LocalDate.of(
                YEAR_RANGE.first + page / 12,
                page % 12 + 1,
                1
            )

            if (page in state.currentPage - 1..state.currentPage + 1) { // 페이징 성능 개선을 위한 조건문
                CalendarMonthItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    currentDate = date,
                    selectedDate = state.selectedDate,
                    onSelectedDate = { state.eventSink(CalendarScreen.Event.DateSelected(it)) }
                )
            }
        }

        AddButton(
            onClick = {
                state.eventSink(CalendarScreen.Event.AddButtonClicked)
            }
        )

        CalendarItemList(itemList = items, selectedDate = state.selectedDate)
    }
}

@Composable
fun CalendarItemList(itemList: List<CalendarItem>, selectedDate: LocalDate) {
    LazyColumn {
        items(itemList) { item ->
            if (item.date == selectedDate) {
                Item(item)
            }
        }
    }
}

@Composable
fun Item(itemData: CalendarItem) {
    Card(
        modifier = Modifier
            .padding(20.dp, 8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = itemData.date.toString(),
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                itemData.emojis.forEach { emoji ->
                    Text(
                        text = emoji,
                        fontSize = 24.sp
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }
    }
}

val items = listOf(
    CalendarItem(
        id = 0,
        date = LocalDate.now(),
        emojis = listOf("😊", "🎉", "🌟")
    ),
    CalendarItem(
        id = 1,
        date = LocalDate.of(2024,6,29),
        emojis = listOf("😊", "🌟", "🎉")
    )
)

@Composable
fun AddButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .padding(20.dp)
            .fillMaxWidth()
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "Add Event")
    }
}


@Composable
fun CalendarHeader(
    modifier: Modifier = Modifier,
    text: String,
) {
    Box(modifier = modifier) {
        Text(
            text = text,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun CalendarMonthItem(
    modifier: Modifier = Modifier,
    currentDate: LocalDate,
    selectedDate: LocalDate,
    onSelectedDate: (LocalDate) -> Unit
) {
    val lastDay = currentDate.lengthOfMonth()
    val firstDayOfWeek = currentDate.dayOfWeek.value
    val days = (1..lastDay).toList()

    Column(modifier = modifier) {
        DayOfWeek()
        LazyVerticalGrid(
            modifier = Modifier.height(300.dp),
            columns = GridCells.Fixed(7)
        ) {
            for (i in 1 until firstDayOfWeek) { // 처음 날짜가 시작하는 요일 전까지 빈 박스 생성
                item {
                    Box(
                        modifier = Modifier
                            .size(30.dp)
                            .padding(top = 10.dp)
                    )
                }
            }
            items(days) { day ->
                val date = currentDate.withDayOfMonth(day)
                val isSelected = remember(selectedDate) {
                    selectedDate.compareTo(date) == 0
                }

                val hasEvent = items.any { it.date == date }

                CalendarDay(
                    modifier = Modifier.padding(top = 20.dp),
                    date = date,
                    isToday = date == LocalDate.now(),
                    isSelected = isSelected,
                    hasEvent = hasEvent,
                    onSelectedDate = onSelectedDate
                )
            }
        }
    }
}

@Composable
fun CalendarDay(
    modifier: Modifier = Modifier,
    date: LocalDate,
    isToday: Boolean,
    isSelected: Boolean,
    hasEvent: Boolean = false,
    onSelectedDate: (LocalDate) -> Unit
) {
    Column(
        modifier = modifier
            .wrapContentSize()
            .size(45.dp, 40.dp)
            .then(
                if (isSelected) {
                    Modifier.border(
                        width = 1.dp,
                        color = Color(0xFF6139A0),
                        shape = RoundedCornerShape(10.dp)
                    )
                } else {
                    Modifier.border(
                        width = 1.dp,
                        color = Color.White,
                        shape = RoundedCornerShape(10.dp)
                    )
                }
            )
            .clip(RoundedCornerShape(10.dp))
            .background(if (isSelected) Color(0xFF8840FF) else Color.White)
            .clickable { onSelectedDate(date) },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val textColor = if (isSelected) Color.White else Color.Black
        Text(
            modifier = Modifier,
            textAlign = TextAlign.Center,
            text = date.dayOfMonth.toString(),
            color = textColor,
            fontWeight = FontWeight.Bold
        )

        //해당 날짜에 아이템이 있을 경우
        if (hasEvent) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(shape = RoundedCornerShape(4.dp))
                    .background(if (isSelected) Color.White else Color.Black)
            )
        }
    }
}

@Composable
fun DayOfWeek(
    modifier: Modifier = Modifier
) {
    Row(modifier) {
        DayOfWeek.entries.forEach { dayOfWeek ->
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                text = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH),
                textAlign = TextAlign.Center,
                color = Color(0xFF9C9D9F)
            )
        }
    }
}
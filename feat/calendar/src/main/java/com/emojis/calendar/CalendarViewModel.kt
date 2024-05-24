package com.emojis.calendar

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
class CalendarViewModel : ViewModel() {

    private val _selectedState = MutableStateFlow<LocalDate>(LocalDate.now())
    val selectedState: StateFlow<LocalDate> = _selectedState.asStateFlow()

    fun onChangeDate(newDate: LocalDate) {
        Log.d("CalendarViewModel", "Changing selected date to: $newDate")
        viewModelScope.launch {
            _selectedState.emit(newDate)
        }
    }
}
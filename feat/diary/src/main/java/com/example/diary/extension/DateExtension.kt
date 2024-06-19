package com.example.diary.extension

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDateTime.getFormattedDate(): String {
    val formatter = DateTimeFormatter.ofPattern( "yyyy년 MM월 dd일" )
    return this.format(formatter)
}
package com.example.diary.model

import android.net.Uri
import androidx.compose.runtime.Immutable

@Immutable
data class Diary(
    val id: String,
    val content: String,
    val date: String,
    val emojis: List<String>,
)
package com.prography.slider

import androidx.compose.runtime.Immutable

@Immutable
data class SliderItem(
    val id: String,
    val content: List<String>, // prompt: List<String> or memo: String
    val url: String,
)
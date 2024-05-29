package com.emojis.create

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
fun ChatScreen() {
    val repository = Impl()
    LaunchedEffect(Unit) {
        repository.login()
    }
    Column {
        Text(text = "테스트 진행중")
    }
}
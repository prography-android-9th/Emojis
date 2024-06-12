package com.example.diary

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun DiaryDetail(state: DiaryScreen.State, modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Absolute.spacedBy(16.dp)
        ) {
            Title(state)
            Content(state)
            SubmitButton(state)
        }
    }
}

@Composable
fun Title(state: DiaryScreen.State) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Absolute.spacedBy(16.dp)
    ) {
        Text(text = state.diary.date, color = Color.Black)
        IconButton(onClick = {
            state.eventSink.invoke(DiaryScreen.Event.BackClicked)
        }) {
            Icon(Icons.Filled.AddCircle, contentDescription = null, tint = Color.LightGray)
        }
    }
}

@Composable
fun Content(state: DiaryScreen.State) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        var content by remember { mutableStateOf(state.diary.content) }
        Row {
            state.diary.emojis.forEach {
                AsyncImage(model = it, contentDescription = "", modifier = Modifier.size(40.dp))
            }
        }
        Spacer(modifier = Modifier.size(16.dp))
        OutlinedTextField(
            minLines = 10,
            value = content,
            onValueChange = { content = it },
        )
    }
}

@Composable
fun SubmitButton(state: DiaryScreen.State) {
    Row(
        horizontalArrangement = Arrangement.End,
    ) {
        Spacer(modifier = Modifier.weight(1f))
        FloatingActionButton(
            onClick = { state.eventSink.invoke(DiaryScreen.Event.submitButtonClicked) }) {
            Icon(Icons.Filled.Check, contentDescription = null)
        }
    }
}
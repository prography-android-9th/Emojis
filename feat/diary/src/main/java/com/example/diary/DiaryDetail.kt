package com.example.diary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage

@Composable
fun DiaryDetail(state: DiaryScreen.State, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .padding(vertical = 80.dp)
    ) {
        ConstraintLayout(modifier = modifier.fillMaxHeight()) {
            val (date, emojiAddButton, emojis, content, submitButton) = createRefs()
            Date(state.diary.date, modifier = modifier.constrainAs(date) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })
            EmojiAddButton(onAddButtonClicked = {
                state.eventSink.invoke(DiaryScreen.Event.BackClicked)
            }, modifier = modifier.constrainAs(emojiAddButton) {
                top.linkTo(date.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })
            Emojis(emojis = state.diary.emojis, modifier = modifier.constrainAs(emojis) {
                top.linkTo(emojiAddButton.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })
            Content(state, modifier = modifier.constrainAs(content) {
                top.linkTo(emojis.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })
            SubmitButton(modifier = modifier.constrainAs(submitButton) {
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
            }, onSubmit = {
                state.eventSink.invoke(DiaryScreen.Event.submitButtonClicked)
            })
        }
    }
}

@Composable
fun Date(date: String, modifier: Modifier = Modifier) =
    Text(text = date, color = Color.Black, modifier = modifier)

@Composable
fun EmojiAddButton(onAddButtonClicked: () -> Unit, modifier: Modifier = Modifier) {
    IconButton(
        modifier = modifier,
        onClick = {
            onAddButtonClicked()
        }) {
        Icon(Icons.Filled.AddCircle, contentDescription = null, tint = Color.LightGray)
    }
}

@Composable
fun Content(state: DiaryScreen.State, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        var content by remember { mutableStateOf(state.diary.content) }
        Spacer(
            modifier = modifier
                .fillMaxWidth(0.9f)
                .padding(vertical = 16.dp)
                .height(1.dp)
                .background(Color.LightGray)
        )
        DiaryContent(
            modifier = modifier,
            content = content,
            onValueChanged = { content = it },
        )
    }
}

@Composable
fun Emojis(emojis: List<String>, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        emojis.forEach {
            AsyncImage(model = it, contentDescription = "", modifier = Modifier.size(40.dp))
        }
    }
}

@Composable
fun DiaryContent(content: String, onValueChanged: (String) -> Unit, modifier: Modifier = Modifier) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(0.9f),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            cursorColor = MaterialTheme.colorScheme.primary,
        ),
        minLines = 10,
        value = content,
        onValueChange = { onValueChanged(it) },
    )
}

@Composable
fun SubmitButton(onSubmit: () -> Unit, modifier: Modifier = Modifier) {
    FloatingActionButton(
        modifier = modifier.padding(end = 20.dp),
        containerColor = MaterialTheme.colorScheme.primary,
        shape = CircleShape,
        onClick = { onSubmit() }) {
        Icon(Icons.Filled.Check, contentDescription = null)
    }
}
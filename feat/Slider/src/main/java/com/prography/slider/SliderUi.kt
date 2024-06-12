@file:OptIn(ExperimentalFoundationApi::class)

package com.prography.slider

import android.util.Log
import com.skydoves.landscapist.glide.GlideImage
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.FlowRow
import com.slack.circuit.runtime.ui.Ui

class SliderUi(): Ui<SliderScreen.State> {

    @Composable
    override fun Content(state: SliderScreen.State, modifier: Modifier) {
        val pagerState = rememberPagerState(pageCount = { state.emojiList.size })

        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DisplayDate(state.currentDate)
                Spacer(modifier = Modifier.height(8.dp))
                CenteredFlowRow(emojis = state.emojiList, pagerState = pagerState)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .weight(2f)
                    .fillMaxWidth()
            ) {
                ImagePager(emojis = state.emojiList, pagerState = pagerState)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                PageIndicators(emojis = state.emojiList, pagerState = pagerState)
                Spacer(modifier = Modifier.height(16.dp))
                CopyButton(emojis = state.emojiList, pagerState = pagerState)
            }
        }
    }

    @OptIn(ExperimentalLayoutApi::class)
    @Composable
    fun CenteredFlowRow(emojis: List<SliderItem>, pagerState: PagerState) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            FlowRow(
                modifier = Modifier.wrapContentWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                emojis[pagerState.currentPage].content.forEach { item ->
                    Box(modifier = Modifier.padding(8.dp)) {
                        ChipItem(text = item)
                    }
                }
            }
        }
    }

    @Composable
    fun DisplayDate(currentDate: String) {
        Text(
            text = currentDate,
            fontSize = 38.sp,
            modifier = Modifier.padding(8.dp)
        )
    }

    @Composable
    fun ImagePager(emojis: List<SliderItem>, pagerState: PagerState) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentPadding = PaddingValues(horizontal = 32.dp)
        ) { page ->
            Box(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
            ) {
                GlideImage(
                    imageModel = emojis[page].url,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    contentScale = ContentScale.Crop,
                )
            }
        }
    }

    @Composable
    fun PageIndicators(emojis: List<SliderItem>, pagerState: PagerState) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            emojis.forEachIndexed { index, _ ->
                Indicator(
                    isSelected = pagerState.currentPage == index,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
            }
        }
    }

    @Composable
    fun CopyButton(emojis: List<SliderItem>, pagerState: PagerState) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom,
        ) {
            Button(
                onClick = { Log.i("TEST", emojis[pagerState.currentPage].url) },
                modifier = Modifier.wrapContentSize(),
                colors = ButtonDefaults.buttonColors(Color.LightGray)
            ) {
                Text(
                    text = "Copy",
                    fontSize = 24.sp,
                    color = Color.White
                )
            }
        }
    }

    @Composable
    fun Indicator(isSelected: Boolean, modifier: Modifier = Modifier) {
        Box(
            modifier = modifier
                .width(if (isSelected) 12.dp else 8.dp)
                .height(if (isSelected) 12.dp else 8.dp)
                .clip(CircleShape)
                .background(if (isSelected) Color.Blue else Color.Gray)
        )
    }

    @Composable
    fun ChipItem(text: String) {
        Box(
            modifier = Modifier
                .background(Color.Gray, shape = RoundedCornerShape(8.dp))
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Text(text = text, color = Color.White)
        }
    }
}



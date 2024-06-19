package com.example.diary

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.diary.extension.getFormattedDate
import com.example.diary.model.Diary
import java.time.LocalDateTime

class DiaryRepository {
    companion object {
        val DEMO =
            Diary(
                id = "1",
                content = "Meeting re-sched!",
                date = "2021년 10월 10일",
                emojis = listOf("https://fastly.picsum.photos/id/935/200/200.jpg?hmac=WNkIQ-NNhosb-4Qfz8Tixwp7-HVS540Z2dS0VDyJ5ac","https://fastly.picsum.photos/id/935/200/200.jpg?hmac=WNkIQ-NNhosb-4Qfz8Tixwp7-HVS540Z2dS0VDyJ5ac","https://fastly.picsum.photos/id/935/200/200.jpg?hmac=WNkIQ-NNhosb-4Qfz8Tixwp7-HVS540Z2dS0VDyJ5ac"),
            )
    }

    private val diary = listOf(DEMO).associateBy { it.id }

    fun getSingleDiary(id: String): Diary {
        return diary.getValue(id)
    }
    fun saveDiaryContent(id: String, content: String) {
       diary.getValue(id).copy(content = content)
    }
}
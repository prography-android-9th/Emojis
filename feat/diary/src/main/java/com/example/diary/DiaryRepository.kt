package com.example.diary

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.diary.extension.getFormattedDate
import com.example.diary.model.Diary
import java.time.LocalDateTime

class DiaryRepository {
    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        val DEMO =
            Diary(
                id = "1",
                content = "Meeting re-sched!",
                date = LocalDateTime.now().getFormattedDate(),
                emojis = listOf("https://fastly.picsum.photos/id/935/200/200.jpg?hmac=WNkIQ-NNhosb-4Qfz8Tixwp7-HVS540Z2dS0VDyJ5ac","https://fastly.picsum.photos/id/935/200/200.jpg?hmac=WNkIQ-NNhosb-4Qfz8Tixwp7-HVS540Z2dS0VDyJ5ac","https://fastly.picsum.photos/id/935/200/200.jpg?hmac=WNkIQ-NNhosb-4Qfz8Tixwp7-HVS540Z2dS0VDyJ5ac"),
            )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private val diary = listOf(DEMO).associateBy { it.id }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getSingleDiary(id: String): Diary {
        return diary.getValue(id)
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun saveDiaryContent(id: String, content: String) {
       diary.getValue(id).copy(content = content)
    }
}
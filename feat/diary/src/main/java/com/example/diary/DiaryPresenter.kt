package com.example.diary

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen

class DiaryPresenter(
    private val screen: DiaryScreen,
    private val navigator: Navigator,
    private val diaryRepository: DiaryRepository,
) : Presenter<DiaryScreen.State> {
    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    override fun present(): DiaryScreen.State {
        val diary = diaryRepository.getSingleDiary(screen.id)
        return DiaryScreen.State(diary) { event ->
            when (event) {
                DiaryScreen.Event.BackClicked -> navigator.pop()
                DiaryScreen.Event.WriteButtonClicked -> navigator.pop()
                DiaryScreen.Event.dateButtonClicked -> navigator.pop()
                DiaryScreen.Event.submitButtonClicked -> navigator.pop()
            }
        }
    }

    class Factory(private val diaryRepository: DiaryRepository) : Presenter.Factory {
        override fun create(
            screen: Screen,
            navigator: Navigator,
            context: CircuitContext,
        ): Presenter<*>? {
            return when (screen) {
                is DiaryScreen -> return DiaryPresenter(screen, navigator, diaryRepository)
                else -> null
            }
        }
    }
}
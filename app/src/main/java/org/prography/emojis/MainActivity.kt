package org.prography.emojis

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.core.view.WindowCompat
import com.example.diary.DiaryDetail
import com.example.diary.DiaryPresenter
import com.example.diary.DiaryRepository
import com.example.diary.DiaryScreen
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.rememberCircuitNavigator

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val emailRepository = DiaryRepository()
        val circuit: Circuit =
            Circuit.Builder()
                .addPresenterFactory(DiaryPresenter.Factory(emailRepository))
                .addUi<DiaryScreen, DiaryScreen.State> { state, modifier -> DiaryDetail(state, modifier) }
                .build()

        enableEdgeToEdge()
        window
            ?.let { WindowCompat.getInsetsController(it, window.decorView) }
            ?.isAppearanceLightStatusBars = true

        setContent {
            MaterialTheme {
                val backStack = rememberSaveableBackStack(DiaryScreen("1"))
                val navigator = rememberCircuitNavigator(backStack)
                CircuitCompositionLocals(circuit) {
                    NavigableCircuitContent(navigator = navigator, backStack = backStack)
                }
            }
        }
    }
}
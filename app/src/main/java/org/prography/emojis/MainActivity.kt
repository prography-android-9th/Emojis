package org.prography.emojis

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.emojis.calendar.CalendarPresenter
import com.emojis.calendar.CalendarScreen
import com.emojis.calendar.CalendarUi
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.rememberCircuitNavigator
import org.prography.emojis.ui.theme.EmojisTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val circuit: Circuit =
            Circuit.Builder()
                .addPresenterFactory(CalendarPresenter.Factory())
                .addUi<CalendarScreen, CalendarScreen.State> { state, modifier -> CalendarUi(modifier, state = state) }
                .build()
        setContent {
            EmojisTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MaterialTheme {
                        val backStack = rememberSaveableBackStack(CalendarScreen("0"))
                        val navigator = rememberCircuitNavigator(backStack)
                        CircuitCompositionLocals(circuit) {
                            NavigableCircuitContent(navigator = navigator, backStack = backStack)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EmojisTheme {
        Greeting("Android")
    }
}
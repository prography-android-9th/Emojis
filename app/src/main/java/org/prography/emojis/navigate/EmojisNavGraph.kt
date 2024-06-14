package org.prography.emojis.navigate

import EmojisPath
import NavigationActions
import NavigationEvent
import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import org.prography.emojis.AppContainer
import subscribeNavigationEvent

/**
 * Created by MyeongKi.
 */
@Composable
fun EmojisNavGraph(
    onEmptyBackStack: () -> Unit
) {
    val navController = rememberNavController()
    val navigationActions = remember(navController) {
        NavigationActions(navController, onEmptyBackStack)
    }
    LaunchedEffect(Unit) {
        AppContainer.navigateEventFlow.subscribeNavigationEvent(
            navActions = navigationActions,
            coroutineScope = this,
        )
    }
    NavHost(
        navController = navController,
        startDestination = EmojisPath.Sample1.getDestination(),
        modifier = Modifier
    ) {
        with(EmojisPath.Sample1) {
            composable(getDestination(), arguments) {
                val scope = rememberCoroutineScope()
                Text(text = "hi", fontSize = 40.sp, modifier = Modifier.clickable {
                    scope.launch {
                        AppContainer.navigateEventFlow.emit(NavigationEvent.NavigateSample2Route("testLoadId"))
                    }

                })
            }
        }
        with(EmojisPath.Sample2()) {
            composable(getDestination(), arguments) {
                val loadId = it.arguments?.getString(EmojisPath.Sample2.ArgumentName.LOAD_ID_KEY.name) ?: ""
                Text(text = "this is sample2 load id : $loadId")
            }
        }
    }
}
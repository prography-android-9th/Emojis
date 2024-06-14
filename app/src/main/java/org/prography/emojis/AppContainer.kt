package org.prography.emojis

import org.prography.navigation.NavigationEvent
import kotlinx.coroutines.flow.MutableSharedFlow

/**
 * Created by MyeongKi.
 */
object AppContainer {
    val navigateEventFlow: MutableSharedFlow<NavigationEvent> = MutableSharedFlow()

}
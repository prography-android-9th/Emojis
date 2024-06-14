package org.prography.navigation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * Created by MyeongKi.
 */
sealed interface NavigationEvent {
    data object NavigateSample1Route : NavigationEvent
    data class NavigateSample2Route(
        val loadId: String
    ) : NavigationEvent

    data object PopBack : NavigationEvent
}

fun MutableSharedFlow<NavigationEvent>.subscribeNavigationEvent(
    navActions: NavigationActions,
    coroutineScope: CoroutineScope
): Job {
    return this
        .onEach {
            when (it) {
                is NavigationEvent.NavigateSample1Route -> {
                    navActions.navigateSample1Route()
                }

                is NavigationEvent.NavigateSample2Route -> {
                    navActions.navigateSample2Route(it.loadId)

                }


                is NavigationEvent.PopBack -> {
                    navActions.popBackStack()
                }
            }
        }
        .launchIn(coroutineScope)
}
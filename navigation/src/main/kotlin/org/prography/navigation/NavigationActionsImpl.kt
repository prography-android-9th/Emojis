package org.prography.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

/**
 * Created by MyeongKi.
 */
class NavigationActions(
    private val navController: NavHostController,
    private val onEmptyBackStack: () -> Unit
) {
    fun navigateSample1Route() {
        navController.navigate(
            EmojisPath.Sample1.getNavigation()
        ) {
            popUpTo(navController.currentDestination?.id ?: navController.graph.findStartDestination().id) {
                saveState = true
            }
        }
    }

    fun navigateSample2Route(
        loadId: String
    ) {
        navController.navigate(
            EmojisPath.Sample2(
                loadId = loadId
            ).getNavigation()
        ) {
            popUpTo(navController.currentDestination?.id ?: navController.graph.findStartDestination().id) {
                saveState = true
            }
        }
    }

    fun popBackStack() {
        if (!navController.popBackStack()) {
            onEmptyBackStack()
        }
    }
}
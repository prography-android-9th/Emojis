package org.prography.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.navArgument

/**
 * Created by MyeongKi.
 */
sealed interface EmojisPath {
    private fun routeHost(): String = this::class.simpleName ?: ""
    val arguments: List<NamedNavArgument>
    fun getDestination(): String {
        val queries: String =
            arguments.map { "${it.name}={${it.name}}" }.takeIf { it.isNotEmpty() }?.fold("") { acc, s ->
                if (acc.isEmpty()) {
                    "?$s"
                } else {
                    "$acc&$s"
                }
            } ?: ""
        return routeHost() + queries
    }

    fun getNavigation(): String {
        val queries: String =
            arguments.map { "${it.name}=${it.argument.defaultValue}" }.takeIf { it.isNotEmpty() }?.fold("") { acc, s ->
                if (acc.isEmpty()) {
                    "?$s"
                } else {
                    "$acc&$s"
                }
            } ?: ""
        return routeHost() + queries

    }

    data object Sample1 : EmojisPath {
        override val arguments: List<NamedNavArgument> = listOf()
    }

    data class Sample2(
        /**
         * 전달 파라미터
         */
        private val loadId: String = "",
    ) : EmojisPath {
        override val arguments: List<NamedNavArgument> = listOf(
            navArgument(ArgumentName.LOAD_ID_KEY.name) { defaultValue = loadId },
        )

        enum class ArgumentName {
            LOAD_ID_KEY,
            ;
        }
    }

}

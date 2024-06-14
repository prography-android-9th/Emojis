import androidx.navigation.NamedNavArgument
import androidx.navigation.navArgument

/**
 * Created by MyeongKi.
 */
sealed interface EmojisPath {
    val routeHost: EmojisRouteHost
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
        return routeHost.name + queries
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
        return routeHost.name + queries

    }

    data object Sample1 : EmojisPath {
        override val routeHost: EmojisRouteHost = EmojisRouteHost.SAMPLE_ROUTE_1
        override val arguments: List<NamedNavArgument> = listOf()
    }

    data class Sample2(
        /**
         * 전달 파라미터
         */
        private val loadId: String = "",
    ) : EmojisPath {
        override val routeHost: EmojisRouteHost = EmojisRouteHost.SAMPLE_ROUTE_2
        override val arguments: List<NamedNavArgument> = listOf(
            navArgument(ArgumentName.LOAD_ID_KEY.name) { defaultValue = loadId },
        )

        enum class ArgumentName {
            LOAD_ID_KEY,
            ;
        }
    }

}
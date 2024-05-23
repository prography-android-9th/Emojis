import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Project
import org.gradle.api.Plugin
import org.gradle.kotlin.dsl.getByType

/**
 * Created by MyeongKi.
 */

class AppComposePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.application")
            val extension = extensions.getByType<ApplicationExtension>()
            configureAndroidCompose(extension)
        }
    }
}
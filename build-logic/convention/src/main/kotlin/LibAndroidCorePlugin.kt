import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

/**
 * Created by MyeongKi.
 */

class LibAndroidCorePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }
            extensions.configure<LibraryExtension> {
                compileSdk = libs.findVersion("androidCompileSdk").get().toString().toInt()
                with(defaultConfig){
                    minSdk = libs.findVersion("androidMinSdk").get().toString().toInt()
                    consumerProguardFiles("consumer-rules.pro")
                }
                with(compileOptions){
                    sourceCompatibility = JavaVersion.VERSION_1_8
                    targetCompatibility = JavaVersion.VERSION_1_8
                }
                buildTypes {
                    release {
                        isMinifyEnabled = true
                        proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
                    }
                }
                dependencies {

                    add("implementation", libs.findLibrary("androidx-core-ktx").get())
                    add("implementation", libs.findLibrary("androidx-appcompat").get())
                    add("implementation", libs.findLibrary("androidx-activity-compose").get())
                    add("implementation", libs.findLibrary("material").get())
                    add("testImplementation", libs.findLibrary("junit").get())
                }
            }
        }
    }
}
import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.Plugin
import org.gradle.kotlin.dsl.configure

/**
 * Created by MyeongKi.
 */

class AppCorePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }
            extensions.configure<ApplicationExtension> {
                compileSdk = libs.findVersion("androidCompileSdk").get().toString().toInt()
                with(defaultConfig){
                    targetSdk = libs.findVersion("androidTargetSdk").get().toString().toInt()
                    minSdk = libs.findVersion("androidMinSdk").get().toString().toInt()
                    vectorDrawables {
                        useSupportLibrary = true
                    }
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
                packaging {
                    resources {
                        excludes += "/META-INF/{AL2.0,LGPL2.1}"
                    }
                }
            }
        }
    }
}
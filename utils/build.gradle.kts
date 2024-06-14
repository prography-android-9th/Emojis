plugins {
    alias(libs.plugins.lib.android.core)
}

android {
    namespace = "org.prography.utils"
    kotlinOptions {
        jvmTarget = "1.8"
    }
}
dependencies {
    implementation(libs.kotlin.date)
}

plugins {
    alias(libs.plugins.lib.android.core)
    alias(libs.plugins.lib.compose)
}

android {
    namespace = "org.prography.nvigation"
    kotlinOptions {
        jvmTarget = "1.8"
    }
}
dependencies {
    implementation(libs.androidx.navigation)
    implementation(libs.kotlin.coroutines)
}
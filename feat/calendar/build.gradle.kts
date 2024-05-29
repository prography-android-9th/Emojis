plugins {
    alias(libs.plugins.lib.android.core)
    alias(libs.plugins.lib.compose)
    id("kotlin-parcelize")
}

android {
    namespace = "org.prography.calendar"
    kotlinOptions {
        jvmTarget = "17"
    }
}
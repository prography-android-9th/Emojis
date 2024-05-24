plugins {
    alias(libs.plugins.lib.android.core)
    alias(libs.plugins.lib.compose)
}

android {
    namespace = "org.prography.calendar"
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

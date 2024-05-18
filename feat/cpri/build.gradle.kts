plugins {
    alias(libs.plugins.lib.android.core)
    alias(libs.plugins.lib.compose)
}

android {
    namespace = "com.prography.cpri"
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

plugins {
    alias(libs.plugins.lib.android.core)
    alias(libs.plugins.lib.compose)
}

android {
    namespace = "org.prography.cpri"
    kotlinOptions {
        jvmTarget = "1.8"
    }
}
dependencies {
    implementation(libs.kord.core)
}

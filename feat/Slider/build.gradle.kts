plugins {
    alias(libs.plugins.lib.android.core)
    alias(libs.plugins.lib.compose)
}

android {
    namespace = "com.prography.slider"
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(libs.landscapist.glide)
}
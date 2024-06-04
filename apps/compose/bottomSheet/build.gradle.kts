plugins {
    id("convention.android.application")
    id("convention.compose")
}

android {
    namespace = "com.compose.bottomSheet"

    defaultConfig {
        applicationId = "com.compose.bottomSheet"
    }
}

dependencies {
    // AndroidX
    implementation(libs.androidX.coreKtx)
    implementation(libs.androidX.appCompat)
    implementation(libs.androidX.lifecycle.viewModel)
    implementation(libs.androidX.activityCompose)

    // Coroutine
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    // Material 3
    implementation(libs.compose.material3)

    implementation(libs.nitrozenAndroid)
    implementation(projects.common.logging)
}
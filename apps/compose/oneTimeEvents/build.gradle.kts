plugins {
    id("convention.android.application")
    id("convention.compose")
}

android {
    namespace = "com.compose.oneTimeEvents"

    defaultConfig {
        applicationId = "com.compose.oneTimeEvents"
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

    implementation(libs.nitrozenAndroid)
    implementation(projects.common.logging)
}
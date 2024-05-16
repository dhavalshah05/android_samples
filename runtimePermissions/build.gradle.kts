plugins {
    id("convention.android.application")
    id("convention.compose")
}

android {
    namespace = "com.sample.runtimePermissions"

    defaultConfig {
        applicationId = "com.sample.runtimePermission"
    }
}

dependencies {
    // AndroidX
    implementation(libs.androidX.coreKtx)
    implementation(libs.androidX.appCompat)
    implementation(libs.androidX.constraintLayout)
    implementation(libs.androidX.lifecycle.viewModel)

    // Coroutine
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    implementation(libs.nitrozenAndroid)
}
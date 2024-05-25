plugins {
    id("convention.android.application")
    id("convention.compose")
}

android {
    namespace = "com.sample.composeNavigation.graph"

    defaultConfig {
        applicationId = "com.sample.composeNavigation.graph"
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

    // Navigation
    implementation(libs.navigation.compose)

    implementation(libs.nitrozenAndroid)
    implementation(projects.common.logging)
    implementation(projects.apps.composeNavigation.common)
}
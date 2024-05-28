plugins {
    id("convention.android.application")
    id("convention.compose")
}

android {
    namespace = "com.sample.paging"

    defaultConfig {
        applicationId = "com.sample.paging"
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

    // Paging
    implementation(libs.paging.runtime)
    implementation(libs.paging.compose)

    // Navigation
    implementation(libs.navigation.compose)

    implementation(libs.nitrozenAndroid)
    implementation(projects.common.logging)
}
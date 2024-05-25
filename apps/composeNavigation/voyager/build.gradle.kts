plugins {
    id("convention.android.application")
    id("convention.compose")
}

android {
    namespace = "com.sample.composeNavigation.voyager"

    defaultConfig {
        applicationId = "com.sample.composeNavigation.voyager"
    }
}

dependencies {
    // AndroidX
    implementation(libs.androidX.coreKtx)
    implementation(libs.androidX.appCompat)
    implementation(libs.androidX.lifecycle.viewModelCompose)
    implementation(libs.androidX.activityCompose)

    // Coroutine
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    // Navigation
    implementation(libs.voyager.navigator)
    implementation(libs.voyager.transitions)
    implementation(libs.voyager.bottomSheetNavigator)

    implementation(libs.nitrozenAndroid)
    implementation(projects.common.logging)
    implementation(projects.apps.composeNavigation.common)
}
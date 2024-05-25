plugins {
    id("convention.android.library")
    id("convention.compose")
}

android {
    namespace = "com.sample.composeNavigation.common"
}

dependencies {
    implementation(libs.androidX.lifecycle.viewModel)
    implementation(libs.androidX.lifecycle.runtimeCompose)

    // Coroutine
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    implementation(libs.nitrozenAndroid)
    implementation(projects.common.logging)
}
plugins {
    id("convention.android.application")
    id("convention.compose")
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.sample.room"

    defaultConfig {
        applicationId = "com.sample.room"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    // Room
    ksp(libs.room.compiler)
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    testImplementation(libs.room.testing)

    testImplementation(libs.junit.junit4)
    androidTestImplementation(libs.androidX.test.junit)
    androidTestImplementation(libs.androidX.test.core)
    androidTestImplementation(libs.androidX.test.rules)
}
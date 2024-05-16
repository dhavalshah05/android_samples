plugins {
    id("convention.android.library")
}

android {
    namespace = "com.common.logging"
}

dependencies {
    api(libs.timber)
}
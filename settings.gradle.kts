rootProject.name = "android_samples"

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { setUrl("https://www.jitpack.io") }
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

// Apps
include(":apps:runtimePermissions")
include(":apps:foregroundService")
include(":apps:room")
include(":apps:composeNavigation:common")
include(":apps:composeNavigation:graph")
// Common
include(":common:logging")
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 33

    defaultConfig {
        namespace = "ee.coo.mobile.wear.tile"
        minSdk = 26
        targetSdk = 33
    }

    buildFeatures {
        compose = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs += "-opt-in=kotlin.RequiresOptIn"
        freeCompilerArgs += "-opt-in=com.google.android.horologist.tiles.ExperimentalHorologistTilesApi"
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.1"
    }
}

dependencies {
    implementation(projects.coreCompose)

    kapt(libs.androidx.hilt.hilt.compiler)
    kapt(libs.com.google.dagger.hilt.compiler)

    implementation(libs.startup.runtime)
    implementation(libs.androidx.compose.material.material.icons.core)
    implementation(libs.androidx.compose.material.material.icons.extended)
    implementation(libs.androidx.wear.tiles)
    implementation(libs.com.google.dagger.hilt.android)
    implementation(libs.horologist.tiles)
    implementation(libs.androidx.wear.tiles.material)
}

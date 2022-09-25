@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("com.squareup.wire") version "4.4.1"
}

android {
    compileSdk = 33

    defaultConfig {
        namespace = "ee.coo.mobile.api"
        minSdk = 26
        targetSdk = 33
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs += "-opt-in=kotlin.RequiresOptIn"
        freeCompilerArgs += "-opt-in=com.google.android.horologist.compose.navscaffold.ExperimentalHorologistComposeLayoutApi"
        freeCompilerArgs += "-opt-in=com.google.android.horologist.tiles.ExperimentalHorologistTilesApi"
        freeCompilerArgs += "-opt-in=com.google.android.horologist.networks.ExperimentalHorologistNetworksApi"
        freeCompilerArgs += "-opt-in=com.google.android.horologist.compose.tools.ExperimentalHorologistComposeToolsApi"
        freeCompilerArgs += "-opt-in=io.rsocket.kotlin.ExperimentalMetadataApi"
    }
}

wire {
    kotlin {
        out = "src/main/kotlin"
        javaInterop = true
    }
}

dependencies {
    kapt(libs.androidx.hilt.hilt.compiler)
    kapt(libs.com.google.dagger.hilt.compiler)

    implementation(libs.androidx.core.core.ktx)
    implementation(libs.com.google.dagger.hilt.android)

    implementation("com.squareup.wire:wire-grpc-client:4.4.1")
    implementation("com.squareup.wire:wire-moshi-adapter:4.4.1")
    implementation("com.squareup.wire:wire-moshi-adapter:4.4.1")
    implementation("com.squareup.wire:wire-runtime:4.4.1")
    implementation("io.rsocket.kotlin:rsocket-core-jvm:0.15.4")
    implementation("io.rsocket.kotlin:rsocket-ktor-client:0.15.4")
    implementation("io.ktor:ktor-client-core-jvm:2.1.1")
    implementation("io.ktor:ktor-client-okhttp:2.1.1")
    implementation("io.ktor:ktor-network-tls:2.1.1")
    implementation("com.squareup.moshi:moshi-adapters:1.14.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.14.0")
    implementation("com.squareup.moshi:moshi:1.14.0")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.10")
    implementation("com.squareup.okhttp3:okhttp-brotli:5.0.0-alpha.10")
    implementation("com.squareup.okhttp3:okhttp-dnsoverhttps:5.0.0-alpha.10")
    implementation("com.squareup.okhttp3:okhttp-sse:5.0.0-alpha.10")
    implementation("com.squareup.okhttp3:okhttp-tls:5.0.0-alpha.10")
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.10")
}

plugins {
    id("rickandmorty.android.library")
    id("rickandmorty.android.hilt")
}

android {
    buildFeatures {
        buildConfig = true
    }
    namespace = "com.icdominguez.rickandmorty.core.network"
}

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.logging.interceptor)
    implementation(libs.kotlin.serialization)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)

    implementation(libs.okhttp.mockwebserver)
    implementation(libs.mockK)
    implementation(libs.kotlinx.testCoroutines)

    implementation(project(":core:common"))
}

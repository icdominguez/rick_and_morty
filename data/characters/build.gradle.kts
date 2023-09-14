plugins {
    id("rickandmorty.android.library")
    id("rickandmorty.android.hilt")
}

android {
    namespace = "com.icdominguez.rickandmorty.data.characters"
}

dependencies {
    implementation(libs.paging.compose)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.kotlinx.testCoroutines)

    implementation(project(":core:model"))
    implementation(project(":core:network"))
    implementation(project(":core:common"))
}

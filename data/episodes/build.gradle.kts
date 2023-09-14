plugins {
    id("rickandmorty.android.library")
    id("rickandmorty.android.hilt")
}

android {
    namespace = "com.icdominguez.rickandmorty.data.episodes"
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:network"))
    implementation(project(":core:common"))
}

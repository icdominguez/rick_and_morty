plugins {
    id("rickandmorty.android.feature")
    id("rickandmorty.android.library.compose")
}

android {
    namespace = "com.icdominguez.rickandmorty.features.characterdetails"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:model"))
    implementation(project(":domain:characters"))
    implementation(project(":domain:episodes"))
}

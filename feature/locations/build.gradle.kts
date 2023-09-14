plugins {
    id("rickandmorty.android.feature")
    id("rickandmorty.android.library.compose")
}

android {
    namespace = "com.icdominguez.rickandmorty.feature.mylibrary"
}

dependencies {
    implementation(project(":core:designsystem"))
}

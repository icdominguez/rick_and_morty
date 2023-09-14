plugins {
    id("rickandmorty.android.feature")
    id("rickandmorty.android.library.compose")
}

android {
    namespace = "com.icdominguez.rickandmorty.feature.episodes"
}

dependencies {
    implementation(project(":core:designsystem"))
}

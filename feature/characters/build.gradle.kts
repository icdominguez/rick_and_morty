plugins {
    id("rickandmorty.android.feature")
    id("rickandmorty.android.library.compose")
}

android {
    namespace = "com.icdominguez.rickandmorty.feature.characters"
}

dependencies {

    with(libs) {
        implementation(paging.compose)
    }

    implementation(libs.bundles.landscapist)

    implementation(project(":domain:characters"))
}

plugins {
    id("rickandmorty.android.library")
    id("rickandmorty.android.hilt")
}

android {
    namespace = "com.icdominguez.rickandmorty.domain.characters"
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.appcompat)

    implementation(project(":data:characters"))
}

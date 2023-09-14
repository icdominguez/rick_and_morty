plugins {
    id("rickandmorty.android.library")
    id("rickandmorty.android.hilt")
}

android {
    namespace = "com.icdominguez.rickandmorty.domain.episodes"
}

dependencies {
    implementation(project(":data:episodes"))
}

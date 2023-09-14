plugins {
    id("rickandmorty.android.library")
}

android {
    namespace = "com.icdominguez.rickandmorty.core.common"
}

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
}

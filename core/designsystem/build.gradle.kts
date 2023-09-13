plugins {
    id("rickandmorty.android.library")
    id("rickandmorty.android.library.compose")
}

android {
    namespace = "com.icdominguez.rickandmorty.core.designsystem"
}

dependencies {

    api(libs.material3)
    api(libs.ui.tooling)
    api(libs.ui.tooling.preview)
    api(libs.design.material.extended)

    with(libs) {
        implementation(core.ktx)
        implementation(lifecycle.runtime.ktx)
        implementation(appcompat)
        implementation(ui)
        implementation(ui.graphics)
        implementation(ui.tooling)
        implementation(ui.tooling.preview)
        implementation(material3)
        implementation(androidx.navigation.compose)
        implementation(design.material.extended)
    }
}

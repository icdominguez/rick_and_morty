plugins {
    id("rickandmorty.android.application")
    id("rickandmorty.android.application.compose")
    id("rickandmorty.android.hilt")
}

android {
    namespace = "com.icdominguez.rickandmorty"
    defaultConfig {
        applicationId = "com.icdominguez.rickandmorty"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    packaging {
        resources {
            excludes += "/META-INF/*"
        }
    }
}

dependencies {

    with(libs) {
        implementation(core.ktx)
        implementation(lifecycle.runtime.ktx)
        implementation(activity.compose)
        implementation(ui)
        implementation(ui.graphics)
        implementation(ui.tooling.preview)
        implementation(androidx.core.splashscreen)
        implementation(material3)
        implementation(androidx.hilt.navigation.compose)
        implementation(design.material.extended)
        testImplementation(junit)
        androidTestImplementation(androidx.test.ext.junit)
        androidTestImplementation(espresso.core)
        androidTestImplementation(ui.test.junit4)
        debugImplementation(ui.tooling)
        debugImplementation(ui.test.manifest)
    }

    implementation(project(":core:designsystem"))
    implementation(project(":feature:characters"))
    implementation(project(":feature:characterdetails"))
    implementation(project(":feature:episodes"))
    implementation(project(":feature:locations"))
}

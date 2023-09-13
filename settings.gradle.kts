pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "rick_and_morty"
include(":app")

include(":feature:characters")
include(":feature:characterdetails")
include(":feature:locations")
include(":feature:episodes")

include(":domain:characters")
include(":data:characters")

include(":core:network")
include(":core:common")
include(":core:model")
include(":core:designsystem")
include(":data:episodes")
include(":domain:episodes")

pluginManagement {
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

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
rootProject.name = "AmazonBookStorePractice"
include(":app")
include(":core:network")
include(":core:domain:booksearch")
include(":core:domain:searchhistory")
include(":core:data:booksearch")
include(":core:data:searchhistory")
include(":core:datastore")
include(":core:design-system")
include(":feature:search")
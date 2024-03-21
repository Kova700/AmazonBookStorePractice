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

include(
    ":app",
    ":core:network",
    ":core:domain:booksearch",
    ":core:domain:searchhistory",
    ":core:data:booksearch",
    ":core:data:searchhistory",
    ":core:datastore",
    ":core:design-system",
    ":feature:search"
)
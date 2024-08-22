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
include(":core:design-system")
include(":feature:main")
include(":core:datastore:datastore")
include(":core:network:network")

include(
	":feature:searchhistory",
	":core:data:searchhistory:external",
	":core:data:searchhistory:internal",
	":core:datastore:searchhistory",
)

include(
	":feature:search",
	":core:data:booksearch:external",
	":core:data:booksearch:internal",
	":core:network:booksearch",
)
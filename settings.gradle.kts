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
	":core:design-system",
	":core:data:booksearch:external",
	":core:data:booksearch:internal",
	":core:data:searchhistory:external",
	":core:data:searchhistory:internal",
	":core:datastore:datastore",
	":core:datastore:searchhistory",
	":core:network:network",
	":core:network:booksearch",
	":feature:search"
)
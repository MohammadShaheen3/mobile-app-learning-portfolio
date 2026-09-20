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

rootProject.name = "MobileAppLearningPortfolio"

include(":module1BusinessCard")
project(":module1BusinessCard").projectDir = file("Module-1/Source-Code/BusinessCardApp")

include(":module2TipCalculator")
project(":module2TipCalculator").projectDir = file("Module-2/Source-Code/TipCalculatorApp")

include(":module3Affirmations")
project(":module3Affirmations").projectDir = file("Module-3/Source-Code/AffirmationsApp")

include(":module4MyCity")
project(":module4MyCity").projectDir = file("Module-4/Source-Code/MyCityApp")

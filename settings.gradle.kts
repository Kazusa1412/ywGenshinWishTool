rootProject.name = "ywGenshinWishTool"
//include("buildSrc")
include("genshinTool-main")

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        maven { url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev") }
    }

}
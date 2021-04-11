import java.io.File
import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.*

group = "com.elouyi"
version = "0.0.1"

val distributionVersion = version.toString()

plugins {
    kotlin("multiplatform") version Versions.kotlin
    kotlin("plugin.serialization") version Versions.kotlin
    id("org.jetbrains.compose") version Versions.compose

}

repositories {
    jcenter() // 居然还要 jcenter
    maven { url = uri("https://dl.bintray.com/kotlin/kotlin-js-wrappers") }
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev") }
    mavenCentral()
}

kotlin {

    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = Versions.jvmTarget
        }

    }

    js(LEGACY) {
        browser {
            binaries.executable()
            webpackTask {
                cssSupport.enabled = true
            }
            runTask {
                cssSupport.enabled = true
            }
            testTask {
                useKarma {
                    useChromeHeadless()
                    webpackConfig.cssSupport.enabled = true
                }
            }
        }
    }

    mingwX64 {
        binaries {
            executable {
                entryPoint = "com.elouyi.main"
            }
        }

        // mingw 路径
        // https://github.com/JetBrains/kotlin/tree/master/kotlin-native/samples/libcurl
        // 照着 sample 打的，没啥用
        val mingwPath = File(System.getenv("MINGW64_DIR") ?: "C:/msys64/mingw64")
        compilations["main"].cinterops {
            val libcurl by creating {
                includeDirs.headerFilterOnly(mingwPath.resolve("include"))
            }
        }
    }

    sourceSets {

        val commonMain by getting {

            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutine}")
                implementation("io.ktor:ktor-client-core:${Versions.ktor}")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinxSerialization}")
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-cio:${Versions.ktor}")
                implementation(compose.desktop.currentOs)
            }
        }

        val jvmTest by getting {

        }

        val jsMain by getting {
            dependencies {
                implementation("org.jetbrains:kotlin-react:${Versions.kotlinReact}")
                implementation("org.jetbrains:kotlin-react-dom:${Versions.kotlinReact}")
                implementation("org.jetbrains:kotlin-styled:${Versions.kotlinStyled}")
                implementation(npm("react",Versions.react))
                implementation(npm("react-dom",Versions.react))
                implementation(npm("styled-components", Versions.styledComponents))
            }
        }

        val jsTest by getting {

        }

        val mingwX64Main by getting {
            languageSettings.useExperimentalAnnotation("kotlin.ExperimentalStdlibApi")
        }
        val mingwX64Test by getting

        all {
            languageSettings.enableLanguageFeature("InlineClasses")
        }
    }
}

compose.desktop {
    application {
        mainClass = "com.elouyi.MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Msi, TargetFormat.Exe)

            outputBaseDir.set(project.buildDir.resolve("customOutputDir"))
            windows {
                // a version for all Windows distributables
                println(project.file("icon.ico").path)
                packageVersion = "0.0.1"
                // a version only for the msi package
                msiPackageVersion = "0.0.1"
                // a version only for the exe package
                exePackageVersion = "0.0.1"
                vendor = "example vender"
                iconFile.set(project.file("icon.ico"))
            }

        }
    }
}
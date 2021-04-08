group = "com.elouyi"
version = "0.1"

plugins {
    kotlin("multiplatform") version Versions.kotlin
}

repositories {
    jcenter() // 居然还要 jcenter
    maven { url = uri("https://dl.bintray.com/kotlin/kotlin-js-wrappers") }
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
    }

    sourceSets {

        val commonMain by getting {

            dependencies {
                implementation(kotlin("stdlib-common"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutine}")
                implementation("io.ktor:ktor-client-core:${Versions.ktor}")

            }
        }

        val jvmMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-cio:${Versions.ktor}")
            }
        }

        val jvmTest by getting {

        }

        val jsMain by getting {
            dependencies {
                implementation("org.jetbrains:kotlin-react:${Versions.kotlinReact}")
                implementation("org.jetbrains:kotlin-react-dom:${Versions.kotlinReact}")
                implementation("org.jetbrains:kotlin-styled:${Versions.kotlinStyled}")
                //implementation("io.ktor:ktor-client-cio:${Versions.ktor}")
                implementation(npm("react",Versions.react))
                implementation(npm("react-dom",Versions.react))
                implementation(npm("styled-components", Versions.styledComponents))
            }
        }

        val jsTest by getting {

        }

        val mingwX64Main by getting {

        }
        val mingwX64Test by getting
    }
}
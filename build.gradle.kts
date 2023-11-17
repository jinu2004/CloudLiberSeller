import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm") version "1.9.0"
    id("org.jetbrains.compose") version "1.5.0"
    kotlin("plugin.serialization") version "1.9.0"
    id("app.cash.sqldelight") version "2.0.0"
}

group = "com.cloudliber"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}
buildscript {
    dependencies {
        classpath("com.guardsquare:proguard-gradle:7.2.0")
        classpath ("org.jetbrains.kotlin:kotlin-serialization:1.6.0")
    }
}


dependencies {
    // Note, if you develop a library, you should use compose.desktop.common.
    // compose.desktop.currentOs should be used in launcher-sourceSet
    // (in a separate module for demo project and in testMain).
    // With compose.desktop.common you will also lose @Preview functionality
    implementation(compose.desktop.currentOs)
    implementation("org.jetbrains.compose.material3:material3-desktop:1.2.1")
    implementation("io.ktor:ktor-client:2.3.5")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("media.kamel:kamel-image:0.7.3")
    implementation("io.ktor:ktor-client-apache5:2.3.5")
    implementation ("io.ktor:ktor-client-serialization:2.3.5")
    implementation ("io.ktor:ktor-client-logging:2.3.5")
    implementation("io.ktor:ktor-client-auth:2.3.5")
    implementation ("ch.qos.logback:logback-classic:1.2.9")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.5")
    implementation("io.ktor:ktor-client-content-negotiation:2.3.5")
    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
    implementation("io.github.thechance101:chart:Beta-0.0.5")
    implementation("app.cash.sqldelight:sqlite-driver:2.0.0")
    implementation("app.cash.sqldelight:coroutines-extensions:2.0.0")

    implementation("dev.icerock.moko:mvvm-core:0.16.1") // only ViewModel, EventsDispatcher, Dispatchers.UI
    implementation("dev.icerock.moko:mvvm-flow:0.16.1") // api mvvm-core, CFlow for native and binding extensions
    implementation("dev.icerock.moko:mvvm-livedata:0.16.1") // api mvvm-core, LiveData and extensions
    implementation("dev.icerock.moko:mvvm-state:0.16.1")

    // compose multiplatform
    implementation("dev.icerock.moko:mvvm-compose:0.16.1") // api mvvm-core, getViewModel for Compose Multiplatfrom
    implementation("dev.icerock.moko:mvvm-flow-compose:0.16.1") // api mvvm-flow, binding extensions for Compose Multiplatfrom
    implementation("dev.icerock.moko:mvvm-livedata-compose:0.16.1")
}

sqldelight{
    databases{
        create("Cloud_Liber_Seller"){
            packageName.set("com.cloudliber")
            srcDirs.setFrom("src/main/sqldelight")
        }
    }
}
compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageVersion = "1.0.0"
            vendor = "CloudLiber.com"
            copyright = "cloudLiber.com"
            includeAllModules = false
            windows.apply {
                shortcut = true
                menu = true
                menuGroup = "CloudLiberSeller"
                iconFile.set(project.file("e70f5442-0b10-4f04-9c45-a7cf85257b18-7.ico"))
            }
            linux.apply {
                shortcut = true
                menuGroup = "CloudLiberSeller"
            }
        }
    }
}





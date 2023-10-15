import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm") version "1.9.0"
    id("org.jetbrains.compose") version "1.5.0"
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
    implementation("io.ktor:ktor-client-serialization:2.3.5")
    implementation("io.ktor:ktor-client-logging:2.3.5")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
}


compose.desktop {

    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "CloudLiberSeller"
            packageVersion = "1.0.0"
            vendor = "CloudLiber.com"
            copyright = "cloudLiber.com"
            includeAllModules = true
            windows.apply {
                shortcut = true
                menu = true
                menuGroup = "CloudLiberSeller"
                includeAllModules = true
            }
            linux.apply {
                shortcut = true
                menuGroup = "CloudLiberSeller"
                includeAllModules = true
            }
            macOS.apply { includeAllModules = true }
        }
    }
}


tasks.create<JavaExec>("obfuscateApp") {
    dependsOn("packageReleaseMsi")

    mainClass.set("proguard.ProGuard") // Replace with the ProGuard main class
    classpath = files("$project.buildDir/libs/MyApp-1.0-SNAPSHOT.jar") // Replace with your JAR filename

    workingDir = project.buildDir

    doLast {
        val outputPath = File(project.buildDir, "distributions")
        outputs.dir(outputPath)
    }
}



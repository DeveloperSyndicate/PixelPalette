plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
    id("maven-publish")
    signing
}

group = "com.devsync.pixelpalettejvm"
version = "1.0.4"

publishing {
    publications {
        create<MavenPublication>("release") {
            // Use the 'java' component instead of 'release'
            from(components["java"])
            groupId = "com.github.DeveloperSyndicate"
            artifactId = "pixelPaletteJvm"
            version = "1.0.4"
        }
    }
    repositories {
        mavenLocal()
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
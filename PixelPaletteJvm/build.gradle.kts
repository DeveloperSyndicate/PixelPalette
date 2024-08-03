plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
    id("maven-publish")
    signing
}

group = "com.devsync.pixelpalettejvm"
version = "1.0.0"

java {
    withJavadocJar()
    withSourcesJar()
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
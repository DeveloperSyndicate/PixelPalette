import com.vanniktech.maven.publish.SonatypeHost

plugins {
    alias(libs.plugins.java.library)
    alias(libs.plugins.jetbrains.kotlin.jvm)
    alias(libs.plugins.maven.publish)
    alias(libs.plugins.gradle.up)
}

group = "com.devsync.pixelpalettejvm"
version = "1.0.1-alpha"

mavenPublishing {
    coordinates("io.github.developersyndicate", "PixelPaletteJvm", "1.0.1-alpha")

    pom {
        name.set("Pixel Palette JVM")
        description.set("PixelPalette is a versatile library for extracting dominant colors and generating palettes from images. Compatible with Android and JVM environments, it simplifies integrating color extraction and palette creation into your projects.")
        inceptionYear.set("2024")
        url.set("https://github.com/DeveloperSyndicate/PixelPalette")
        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                distribution.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }
        developers {
            developer {
                id.set("DeveloperSyndicate")
                name.set("Sanjay")
                url.set("https://github.com/DeveloperSyndicate/")
            }
        }
        scm {
            url.set("https://github.com/DeveloperSyndicate/PixelPalette/")
            connection.set("scm:git:git://github.com/DeveloperSyndicate/PixelPalette.git")
            developerConnection.set("scm:git:ssh://git@github.com/DeveloperSyndicate/PixelPalette.git")
        }
    }

    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
    signAllPublications()
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

dependencies {
    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlinx.coroutines.core)
}
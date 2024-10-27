import com.vanniktech.maven.publish.SonatypeHost

plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
    id("com.vanniktech.maven.publish") version "0.30.0"
    id("com.gradleup.nmcp") version "0.0.7" apply false
}

group = "com.devsync.pixelpalettejvm"
version = "1.0.0"

mavenPublishing {
    coordinates("io.github.developersyndicate", "PixelPaletteJvm", "1.0.0")

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
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
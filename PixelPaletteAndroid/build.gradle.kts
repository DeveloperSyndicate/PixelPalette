import com.vanniktech.maven.publish.SonatypeHost

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.maven.publish)
    alias(libs.plugins.gradle.up)
    alias(libs.plugins.dokka)
}

android {
    namespace = "com.devsync.pixelpalette.android"
    compileSdk = 35

    defaultConfig {
        minSdk = 23
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isJniDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

mavenPublishing {
    coordinates("io.github.developersyndicate", "PixelPaletteAndroid", "1.0.2")

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

dependencies {
    implementation(libs.androidx.core.ktx)
}

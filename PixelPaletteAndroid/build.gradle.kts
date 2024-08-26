plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("maven-publish")
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
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        val javaVersion = JavaVersion.VERSION_17
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.github.DeveloperSyndicate"
            artifactId = "pixelPaletteAndroid"
            version = "1.0.0"
            pom {
                description = "First release"
            }
            afterEvaluate {
                from(components["release"])
            }
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


java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
import org.gradle.kotlin.dsl.invoke

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("io.qameta.allure") version "2.12.0"
}

kotlin {
    jvmToolchain(17)
}

android {
    namespace = "com.example.alkaa.kaspresso"
    compileSdk = 36

    defaultConfig {
        minSdk = 26
        targetSdk = 34
        testInstrumentationRunner = "com.kaspersky.kaspresso.runner.KaspressoRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
        debug {
            enableUnitTestCoverage = true
        }
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility(JavaVersion.VERSION_17)
        targetCompatibility(JavaVersion.VERSION_17)
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        viewBinding = true
    }

    testOptions {
        animationsDisabled = true
    }

    lint {
        abortOnError = false
    }
}

dependencies {

    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.3")
    // Core AndroidX тесты
    implementation("androidx.test:core:1.6.1")
    implementation("androidx.test:runner:1.6.2")
    implementation("androidx.test.ext:junit:1.2.1")

    // Kaspresso
    androidTestImplementation("com.kaspersky.android-components:kaspresso:1.6.0")
    androidTestImplementation("com.kaspersky.android-components:kaspresso-allure-support:1.6.0")

    // Allure Kotlin
    androidTestImplementation("io.qameta.allure:allure-kotlin-android:2.4.0")
    androidTestImplementation("io.qameta.allure:allure-kotlin-junit4:2.4.0")

    // Espresso / UIAutomator
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation("androidx.test.espresso:espresso-intents:3.6.1")
    androidTestImplementation("androidx.test.uiautomator:uiautomator:2.3.0")

    // Appium (по желанию)
    testImplementation("io.appium:java-client:10.0.0")
    testImplementation("org.seleniumhq.selenium:selenium-java:4.35.0")

    // Ассерты Kotlin
    androidTestImplementation(kotlin("test"))
}
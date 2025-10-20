plugins {
    kotlin("jvm")
    id("io.qameta.allure") version "2.12.0"
}

repositories {
    mavenCentral()
    google()
}

kotlin {
    jvmToolchain(21)
}

dependencies {
    testImplementation("io.insert-koin:koin-core:3.5.6")
    testImplementation("io.insert-koin:koin-test:3.5.6")
    testImplementation("io.insert-koin:koin-test-junit5:3.5.6")
    testImplementation(project(":domain"))

    testImplementation(platform("org.junit:junit-bom:5.10.3"))
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")


    testImplementation("io.qameta.allure:allure-junit5:2.29.0")


    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.9.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-datetime:0.6.0")
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()

    notCompatibleWithConfigurationCache("Allure Gradle adapter не полностью совместим с configuration cache")

    testLogging {
        events("passed", "failed", "skipped", "standardOut", "standardError")
        showExceptions = true
        showStackTraces = true
        showCauses = true
    }

    maxParallelForks = 1
    forkEvery = 0
    jvmArgs(
        "--add-opens=java.base/java.lang=ALL-UNNAMED",
        "--add-opens=java.base/java.lang.invoke=ALL-UNNAMED"
    )
}

allure {
    adapter {
        frameworks { junit5 { enabled.set(true) } }
        autoconfigure.set(true)
        aspectjWeaver.set(false)
    }
    report { version.set("2.29.0") }
}

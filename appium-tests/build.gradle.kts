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
    implementation(platform("org.seleniumhq.selenium:selenium-bom:4.23.0"))
    implementation("org.seleniumhq.selenium:selenium-api")
    implementation("org.seleniumhq.selenium:selenium-remote-driver")
    implementation("org.seleniumhq.selenium:selenium-support")

    implementation("io.appium:java-client:10.0.0")

    testImplementation("org.junit.jupiter:junit-jupiter:5.10.3")
    testImplementation("io.qameta.allure:allure-junit5:2.21.0")
    testImplementation(kotlin("test"))
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


tasks.test { useJUnitPlatform() }
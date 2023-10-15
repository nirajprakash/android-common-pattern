val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val koin_version: String by project
val koin_ktor: String by project
//val ksp_version: String by project
val koin_ksp_version: String by project

plugins {

    kotlin("jvm") version "1.9.10"

    id("io.ktor.plugin") version "2.3.4"
//    id("com.google.devtools.ksp") version "1.9.10-1.0.13"

    application

}

group = "io.github.nirajprakash.apiktor"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/ktor/eap") }
}
// Use KSP Generated sources
//sourceSets.main {
//    java.srcDirs("build/generated/ksp/main/kotlin")
//}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")

    // Koin for Ktor
    implementation("io.insert-koin:koin-ktor:$koin_version")
    // SLF4J Logger
    implementation("io.insert-koin:koin-logger-slf4j:$koin_version")
    implementation("io.insert-koin:koin-core:$koin_version")
//    implementation("io.insert-koin:koin-annotations:$koin_ksp_version")
//    ksp("io.insert-koin:koin-ksp-compiler:$koin_ksp_version")
//    testImplementation("io.insert-koin:koin-test:$koin_version")

    testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

application {
//    mainClass.set("io.github.nirajprakash.apiktor.ApplicationKt")
    mainClass.set("io.ktor.server.netty.EngineMain")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}


//application {
//    mainClass.set("MainKt")
//}
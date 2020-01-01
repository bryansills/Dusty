import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    application
    kotlin("jvm")
    id("com.heroku.sdk.heroku-gradle") version "1.0.4"
    id("com.github.johnrengelman.shadow") version "5.2.0"
}

group = "ninja.bryansills.dusty"
version = "0.0.1-SNAPSHOT"

application {
    mainClassName = "io.ktor.server.netty.EngineMain"
}

repositories {
    mavenLocal()
    jcenter()
    maven { url = uri("https://kotlin.bintray.com/ktor") }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.3.61")
    implementation("io.ktor:ktor-server-netty:1.2.6")
    implementation("ch.qos.logback:logback-classic:1.2.3")
    implementation("io.ktor:ktor-server-core:1.2.6")
    testImplementation("io.ktor:ktor-server-tests:1.2.6")
}

tasks {
    named<ShadowJar>("shadowJar") {
        archiveBaseName.set("dusty-server")
        archiveClassifier.set(null as String?)
        archiveVersion.set(null as String?)
    }
}

heroku {
    appName = "dusty-auth"
}
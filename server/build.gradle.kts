plugins {
    application
    kotlin("jvm")
    id("com.heroku.sdk.heroku-gradle") version "1.0.4"
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

kotlin.sourceSets["main"].kotlin.srcDirs("src")
kotlin.sourceSets["test"].kotlin.srcDirs("test")

sourceSets["main"].resources.srcDirs("resources")
sourceSets["test"].resources.srcDirs("testresources")

heroku {
    appName = "dusty-auth"
}
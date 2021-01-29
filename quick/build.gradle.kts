import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val SPOTIFY_CLIENT_ID: String by project
val SPOTIFY_CLIENT_SECRET: String by project
val SPOTIFY_REDIRECT_URI: String by project
val SPOTIFY_AUTHORIZATION_CODE: String by project

plugins {
    id("application")
    kotlin("jvm")
}

application {
    mainClassName = "ninja.bryansills.dusty.quick.MainKt"
}

repositories {
    mavenCentral()
    google()
    maven(url = "https://jitpack.io")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")
    implementation(project(":network"))
    implementation(project(":network-auth"))
    implementation("io.reactivex.rxjava2:rxjava:2.2.10")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.run.configure {
    args = listOf(
        SPOTIFY_CLIENT_ID,
        SPOTIFY_CLIENT_SECRET,
        SPOTIFY_REDIRECT_URI,
        SPOTIFY_AUTHORIZATION_CODE
    )
}
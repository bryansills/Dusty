import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val SPOTIFY_AUTHENTICATION_TOKEN: String by project

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
    implementation(project(":network"))
    implementation("io.reactivex.rxjava2:rxjava:2.2.10")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.run.configure {
    args = listOf(SPOTIFY_AUTHENTICATION_TOKEN)
}
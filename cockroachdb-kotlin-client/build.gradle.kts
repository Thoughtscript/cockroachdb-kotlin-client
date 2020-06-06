plugins {
    application
    kotlin("jvm") version "1.3.72"
}

version = "1.0.0"
group = "com.cockroachlabs.client"

application {
    mainClass.set("com.cockroachlabs.client.MainKt")
}

repositories {
    mavenCentral()
}

dependencies {
    api("org.postgresql:postgresql:42.2.13.jre7")
    implementation(kotlin("stdlib"))
}

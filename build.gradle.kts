plugins {
    val kotlinVersion = "1.8.10"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion

    id("net.mamoe.mirai-console") version "2.15.0-RC"
}

group = "cn.bsgot"
version = "0.1.0"

repositories {
    maven("https://maven.aliyun.com/repository/public")
    maven("https://repo.hypixel.net/repository/Hypixel/")
    mavenCentral()
}

dependencies {
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation("cn.hutool:hutool-all:5.8.16")
    implementation("net.hypixel:hypixel-api-transport-apache:4.3")
}

mirai {
    jvmTarget = JavaVersion.VERSION_1_8
}

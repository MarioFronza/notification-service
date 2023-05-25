// Define version variables using delegated properties
val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val koin_version: String by project
val rabbit_version: String by project
val jda_version: String by project
val telegram_bot_version: String by project

// Configure plugins
plugins {
    kotlin("jvm") version "1.8.20"
    id("io.ktor.plugin") version "2.2.4"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.20"
}

// Set project information
group = "com.github"
version = "0.0.1"

// Configure application
application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

// Configure repositories
repositories {
    mavenCentral()
}

// Define project dependencies
dependencies {
    implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-locations:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("io.insert-koin:koin-ktor:$koin_version")
    implementation("com.rabbitmq:amqp-client:$rabbit_version")
    implementation("net.dv8tion:JDA:$jda_version")
    implementation("com.github.pengrad:java-telegram-bot-api:$telegram_bot_version")
    testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}

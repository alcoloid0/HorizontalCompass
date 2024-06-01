import java.util.Calendar

plugins {
    id("java")
    id("org.cadixdev.licenser") version "0.6.1"
}

group = "omg.alcoloid"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.essentialsx.net/releases/")
    maven("https://repo.dmulloy2.net/repository/public/")
}

dependencies {
    // Paper API
    compileOnly("io.papermc.paper:paper-api:1.20.4-R0.1-SNAPSHOT")

    // Essentials
    compileOnly("net.essentialsx:EssentialsX:2.20.0")

    // ProtocolLib
    compileOnly("com.comphenix.protocol:ProtocolLib:5.1.0")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

tasks.processResources {
    filesMatching("**/plugin.yml") {
        expand("version" to project.version)
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

license {
    header = resources.text.fromFile(file("HEADER.txt"))
    properties {
        set("author", "alcoloid (alcoloid0)")
        set("year", Calendar.getInstance().get(Calendar.YEAR))
    }
    exclude("**/*.yml")
}
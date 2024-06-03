import java.util.Calendar

plugins {
    id("java")
    id("org.cadixdev.licenser") version "0.6.1"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "com.github.alcoloid0"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()

    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://repo.essentialsx.net/releases/")
    maven("https://repo.dmulloy2.net/repository/public/")

    // For EssentialsX dependency
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    // Spigot API
    compileOnly("org.spigotmc:spigot-api:1.20.4-R0.1-SNAPSHOT")

    // Essentials
    compileOnly("net.essentialsx:EssentialsX:2.20.0")

    // ProtocolLib
    compileOnly("com.comphenix.protocol:ProtocolLib:5.1.0")

    // Adventure
    implementation("net.kyori:adventure-platform-bukkit:4.3.2")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

tasks {
    processResources {
        filesMatching("**/plugin.yml") {
            expand("version" to project.version)
        }
    }

    withType<JavaCompile> {
        options.encoding = "UTF-8"
    }

    shadowJar {
        relocate("net.kyori.adventure", "com.github.alcoloid0.shaded.kyori.adventure")
    }
}

license {
    header = resources.text.fromFile("HEADER.txt")
    properties {
        set("author", "alcoloid (alcoloid0)")
        set("year", Calendar.getInstance().get(Calendar.YEAR))
    }
    exclude("**/*.yml")
}
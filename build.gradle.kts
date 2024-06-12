import java.util.*

plugins {
    id("java")
    id("maven-publish")
    id("org.cadixdev.licenser") version "0.6.1"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

allprojects {
    apply(plugin = "java")
    apply(plugin = "maven-publish")
    apply(plugin = "org.cadixdev.licenser")

    group = "com.github.alcoloid0"
    version = "1.0-SNAPSHOT"

    java {
        toolchain.languageVersion.set(JavaLanguageVersion.of(17))
        withSourcesJar()
    }

    license {
        header = resources.text.fromFile(rootProject.file("HEADER.txt"))
        properties {
            set("author", "alcoloid (alcoloid0)")
            set("year", Calendar.getInstance().get(Calendar.YEAR))
        }
        exclude("**/*.yml")
    }

    tasks.processResources {
        filesMatching("**/plugin.yml") {
            expand("version" to project.version)
        }
    }

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
    }

    publishing {
        publications {
            register("mavenJava", MavenPublication::class) {
                from(components["java"])
            }
        }
    }

    repositories {
        mavenCentral()
        maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    }

    dependencies {
        compileOnly("org.jetbrains:annotations:24.0.0")
        compileOnly("org.spigotmc:spigot-api:1.20.4-R0.1-SNAPSHOT")
    }
}

repositories {
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://repo.dmulloy2.net/repository/public/")
}

dependencies {
    // ProtocolLib
    compileOnly("com.comphenix.protocol:ProtocolLib:5.1.0")

    // Adventure
    implementation("net.kyori:adventure-platform-bukkit:4.3.3")

    // Adventure Serializer Configurate4
    implementation("net.kyori:adventure-serializer-configurate4:4.11.0")

    // Configurate
    implementation("org.spongepowered:configurate-yaml:4.0.0")

    // API
    implementation(project(":api"))
}

tasks {
    shadowJar {
        relocate("net.kyori.adventure", "com.github.alcoloid0.shaded.kyori.adventure")
    }
}
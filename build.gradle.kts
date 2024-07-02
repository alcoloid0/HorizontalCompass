import java.util.*

plugins {
    id("java")
    id("maven-publish")
    id("org.cadixdev.licenser") version "0.6.1"
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "maven-publish")
    apply(plugin = "org.cadixdev.licenser")

    group = "com.github.alcoloid0"
    version = "1.1"

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
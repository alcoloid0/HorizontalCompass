plugins {
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

repositories {
    maven("https://repo.dmulloy2.net/repository/public/")
}

dependencies {
    // ProtocolLib
    compileOnly("com.comphenix.protocol:ProtocolLib:5.1.0")

    // Adventure
    implementation("net.kyori:adventure-platform-bukkit:4.3.4")

    // Adventure Serializer Configurate4
    implementation("net.kyori:adventure-serializer-configurate4:4.11.0")

    // Configurate
    implementation("org.spongepowered:configurate-yaml:4.0.0")

    // API
    implementation(project(":api"))
}

tasks.shadowJar {
    relocate("net.kyori.adventure", "com.github.alcoloid0.horizontalcompass.shaded.adventure")
    relocate("net.kyori.option","com.github.alcoloid0.horizontalcompass.shaded.option")
}

java {
    base.archivesName.set(rootProject.name)
}
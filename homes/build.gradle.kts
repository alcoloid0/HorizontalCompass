repositories {
    maven("https://repo.essentialsx.net/releases/")
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    compileOnly("net.essentialsx:EssentialsX:2.20.0")
    implementation(project(":api"))
}
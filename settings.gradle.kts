pluginManagement {
    repositories {
        maven { url = uri("https://maven.fabricmc.net/") }
        maven { url = uri("https://maven.architectury.dev/") }
        maven { url = uri("https://files.minecraftforge.net/maven/") }
        gradlePluginPortal()
    }
}

rootProject.name = "caesar"

sequenceOf("common", "fabric", "neoforge").forEach {
    val project = ":${rootProject.name}-$it"
    include(project)
    project(project).projectDir = file(it)
}
import dev.architectury.plugin.ArchitectPluginExtension
import dev.architectury.plugin.ArchitecturyPlugin
import net.fabricmc.loom.api.LoomGradleExtensionAPI

plugins {
    id("java")
    id("architectury-plugin") version "3.4-SNAPSHOT"
    id("dev.architectury.loom") version "1.6-SNAPSHOT" apply false
    id("com.github.johnrengelman.shadow") version "8.1.1" apply false
}

val minecraft_version: String by project.extra
val maven_group: String by project.extra
val mod_version: String by project.extra

configure<ArchitectPluginExtension> {
    minecraft = minecraft_version
}

allprojects {
    group = maven_group
    version = mod_version
}

subprojects {
    apply<JavaPlugin>()
    apply(plugin = "dev.architectury.loom")
    apply<ArchitecturyPlugin>()

    repositories {
    }

    configure<LoomGradleExtensionAPI> {
        silentMojangMappingsLicense()
    }

    dependencies {
        "minecraft"("net.minecraft:minecraft:$minecraft_version")
        "mappings"(project.extensions.getByName<LoomGradleExtensionAPI>("loom").officialMojangMappings())
    }

    java {
        withSourcesJar()

        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    tasks.withType(JavaCompile::class.java) {
        options.encoding = "UTF-8"
        options.release.set(21)
    }
}

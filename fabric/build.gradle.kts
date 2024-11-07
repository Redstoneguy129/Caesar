plugins {
    id("com.github.johnrengelman.shadow")
}

val fabric_loader_version: String by project.extra
val fabric_api_version: String by project.extra
val architectury_api_version: String by project.extra

architectury {
    platformSetupLoomIde()
    fabric()
}

val common: Configuration by configurations.creating
val shadowBundle: Configuration by configurations.creating
val developmentFabric: Configuration by configurations.getting

configurations {
    compileClasspath.get().extendsFrom(configurations["common"])
    runtimeClasspath.get().extendsFrom(configurations["common"])
    developmentFabric.extendsFrom(configurations["common"])
}

dependencies {
    "modImplementation"("net.fabricmc:fabric-loader:$fabric_loader_version")

    "modImplementation"("net.fabricmc.fabric-api:fabric-api:$fabric_api_version")

    "modImplementation"("dev.architectury:architectury-fabric:$architectury_api_version")

    common(project(":caesar-common", "namedElements")) { isTransitive = false }
    shadowBundle(project(":caesar-common", "transformProductionFabric")) { isTransitive = false }
}

tasks {
    processResources {
        inputs.property("version", project.version)

        filesMatching("fabric.mod.json") {
            expand(
                mapOf(
                    "version" to project.version,
                )
            )
        }
    }

    shadowJar {
        configurations = listOf(shadowBundle)
        archiveClassifier.set("dev-shadow")
    }

    remapJar {
        injectAccessWidener.set(true)
        inputFile.set(shadowJar.get().archiveFile)
        dependsOn(shadowJar)
        archiveClassifier.set(null as String?)
    }
}
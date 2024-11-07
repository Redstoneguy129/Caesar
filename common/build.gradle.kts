val enabled_platforms: String by project.extra
val fabric_loader_version: String by project.extra
val architectury_api_version: String by project.extra

architectury {
    common(enabled_platforms.split(','))
}

dependencies {
    "modImplementation"("net.fabricmc:fabric-loader:$fabric_loader_version")

    "modImplementation"("dev.architectury:architectury:$architectury_api_version")
}

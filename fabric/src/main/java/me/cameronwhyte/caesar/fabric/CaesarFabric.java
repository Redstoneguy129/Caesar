package me.cameronwhyte.caesar.fabric;

import net.fabricmc.api.ModInitializer;

import me.cameronwhyte.caesar.Caesar;

public final class CaesarFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        Caesar.init();
    }
}

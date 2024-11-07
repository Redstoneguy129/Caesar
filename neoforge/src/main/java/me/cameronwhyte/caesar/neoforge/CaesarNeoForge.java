package me.cameronwhyte.caesar.neoforge;

import net.neoforged.fml.common.Mod;

import me.cameronwhyte.caesar.Caesar;

@Mod(Caesar.MOD_ID)
public final class CaesarNeoForge {
    public CaesarNeoForge() {
        // Run our common setup.
        Caesar.init();
    }
}

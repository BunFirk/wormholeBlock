package org.eatbun.wormholeblock;

import net.fabricmc.api.ModInitializer;

import static org.eatbun.wormholeblock.block.blockRegister.registerModBlocks;

public class Wormholeblock implements ModInitializer {

    public static final String MOD_ID = "wormholeblock";

    @Override
    public void onInitialize() {
        registerModBlocks(); // регистрация блоков
    }
}

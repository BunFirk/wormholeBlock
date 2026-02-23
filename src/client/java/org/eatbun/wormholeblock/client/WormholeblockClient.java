package org.eatbun.wormholeblock.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import org.eatbun.wormholeblock.block.blockRegister;


public class WormholeblockClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        BlockRenderLayerMap.INSTANCE.putBlock( // делаю блоки прозрачными
                blockRegister.WORMHOLE_GRAVITY_BLOCK,
                RenderLayer.getCutout()
        );

        BlockRenderLayerMap.INSTANCE.putBlock(
                blockRegister.WORMHOLE_INVENTORY_BLOCK,
                RenderLayer.getCutout()
        );
    }
}

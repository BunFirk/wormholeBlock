package org.eatbun.wormholeblock.block;


import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import org.eatbun.wormholeblock.Wormholeblock;

public class blockRegister {

    // регистрация блоков
    public static final Block WORMHOLE_WITHER_BLOCK = registerBlock("wormhole_block", new WormholeBlock(FabricBlockSettings.copyOf(Blocks.OBSIDIAN).sounds(BlockSoundGroup.GLASS)));
    public static final Block WORMHOLE_GRAVITY_BLOCK = registerBlock("wormhole_gravity_block", new WormholeGravityBlock(FabricBlockSettings.copyOf(Blocks.OBSIDIAN).sounds(BlockSoundGroup.GLASS)));
    public static final Block WORMHOLE_INVENTORY_BLOCK = registerBlock("wormhole_inventory_block", new WormholeInventoryBlock(FabricBlockSettings.copyOf(Blocks.OBSIDIAN).sounds(BlockSoundGroup.GLASS)));

    private static Block registerBlock(String name, Block block){
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(Wormholeblock.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(Wormholeblock.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(ent -> {
            ent.add(WORMHOLE_WITHER_BLOCK);
            ent.add(WORMHOLE_GRAVITY_BLOCK);
            ent.add(WORMHOLE_INVENTORY_BLOCK); // добавляю блоки в конец списка натуральные блоки
        });
    }

}

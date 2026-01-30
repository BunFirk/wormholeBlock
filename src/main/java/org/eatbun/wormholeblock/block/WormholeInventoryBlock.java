package org.eatbun.wormholeblock.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

public class WormholeInventoryBlock extends Block {

    public WormholeInventoryBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        world.scheduleBlockTick(pos, this, 20); // первый тик
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, net.minecraft.util.math.random.Random random) {


        // если человек подходит на 10 блоков, то из его ведущей и под руки выкидываются предметы
        world.getEntitiesByClass(PlayerEntity.class, new Box(pos).expand(10), p -> true)
                .forEach(player -> {

                    // правая рука
                    ItemStack mainHand = player.getStackInHand(Hand.MAIN_HAND);
                    if (!mainHand.isEmpty()) {
                        player.dropItem(mainHand.copy(), true);
                        player.setStackInHand(Hand.MAIN_HAND, ItemStack.EMPTY);
                    }

                    // левая рука
                    ItemStack offHand = player.getStackInHand(Hand.OFF_HAND);
                    if (!offHand.isEmpty()) {
                        player.dropItem(offHand.copy(), true);
                        player.setStackInHand(Hand.OFF_HAND, ItemStack.EMPTY);
                    }
                });


        // если человек подошёл на 5 блоков, то из его хотбара выкидываются все предметы
        world.getEntitiesByClass(PlayerEntity.class, new Box(pos).expand(5), p -> true)
                .forEach(player -> {
                        for (int slot = 0; slot <= 8; slot++){
                            ItemStack stack = player.getInventory().getStack(slot);
                            if (!stack.isEmpty()) {
                                player.dropItem(stack.copy(), true);
                                player.getInventory().setStack(slot, ItemStack.EMPTY);
                            }
                        }
                }
                );


        // накладываю эффект иссушения при 3 блоках
        world.getEntitiesByClass(PlayerEntity.class, new Box(pos).expand(3), p -> true)
                .forEach(player -> player.addStatusEffect(
                        new StatusEffectInstance(
                                StatusEffects.WITHER, // Иссушение
                                60,                   // 3 секунды
                                3                     // уровень
                        )
                ));

        world.scheduleBlockTick(pos, this, 20); // повторять каждую секунду
    }
}
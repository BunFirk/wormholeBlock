package org.eatbun.wormholeblock.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

public class WormholeBlock extends Block {

    public WormholeBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        world.scheduleBlockTick(pos, this, 20); // первый тик
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, net.minecraft.util.math.random.Random random) {

        // при 10 блоках от разлома накладываю отравление 1 уровня
        world.getEntitiesByClass(PlayerEntity.class, new Box(pos).expand(10), p -> true)
                .forEach(player -> player.addStatusEffect(
                        new StatusEffectInstance(
                                StatusEffects.POISON, //️ отравление
                                60,                   // 3 секунды
                                0                     // уровень
                        )
                ));


        // накладываю эффект тьма при 5 блоках
        world.getEntitiesByClass(PlayerEntity.class, new Box(pos).expand(5), p -> true)
                .forEach(player -> player.addStatusEffect(
                        new StatusEffectInstance(
                                StatusEffects.DARKNESS, // тьма
                                60,                   // 3 секунды
                                2                     // уровень
                        )
                ));


        // при 3 блоках накладываю иссушение
        world.getEntitiesByClass(PlayerEntity.class, new Box(pos).expand(3), p -> true)
                .forEach(player -> player.addStatusEffect(
                        new StatusEffectInstance(
                                StatusEffects.WITHER, // Иссушение
                                60,                   // 3 секунды
                                2                     // уровень
                        )
                ));

        world.scheduleBlockTick(pos, this, 20); // повторять каждую секунду
    }
}
package net.hazen.hazentouvelib.Registries;

import net.hazen.hazentouvelib.Blocks.SoulFire.SoulFireBlock;
import net.hazen.hazentouvelib.HazentouveLib;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static net.minecraft.world.level.block.Blocks.FIRE;

public class HLBlockRegistry {
        public static final DeferredRegister.Blocks BLOCKS =
                DeferredRegister.createBlocks(HazentouveLib.MOD_ID);

        public static final DeferredBlock<Block> SOUL_FIRE = registerBlock("soul_fire",
                () -> new SoulFireBlock(
                        BlockBehaviour.Properties
                                .ofFullCopy(FIRE)
                                .noLootTable()
                                        .mapColor(MapColor.COLOR_LIGHT_BLUE)
                                        .lightLevel((state) -> 10)
                        ));


        private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
                DeferredBlock<T> toReturn = BLOCKS.register(name, block);
                return toReturn;
        }

        public static void register(IEventBus eventBus) {
                BLOCKS.register(eventBus);
        }

}
package net.hazen.hazentouvelib.Registries;

import net.hazen.hazentouvelib.HazentouveLib;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class HLBlockRegistry {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(HazentouveLib.MOD_ID);

    public static final DeferredBlock<Block> STEEL_BLOCK = BLOCKS.registerBlock("steel_block",
            properties -> new Block(properties
                    .mapColor(MapColor.METAL)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(0.8F)
                    .sound(SoundType.IRON)
            ));

    public static final DeferredBlock<Block> CRUDE_METAL_BLOCK = BLOCKS.registerBlock("crude_metal_block",
            properties -> new Block(properties
                    .mapColor(MapColor.METAL)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(0.8F)
                    .sound(SoundType.STONE)
            ));


    public static void register(IEventBus modEventBus){
        BLOCKS.register(modEventBus);
    }
}
package net.hazen.hazentouvelib.Registries;

import net.hazen.hazentouvelib.HazentouveLib;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class HLItemRegistry {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(HazentouveLib.MOD_ID);

    public static final DeferredItem<Item> CRUDE_METAL = ITEMS.registerItem(
            "crude_metal",
            Item::new);

    public static final DeferredItem<Item> STEEL_INGOT = ITEMS.registerItem(
            "steel_ingot",
            Item::new);

    public static final DeferredItem<Item> STEEL_NUGGET = ITEMS.registerItem(
            "steel_nugget",
            Item::new);

    /*
    *** Blocks
     */

    public static final DeferredItem<BlockItem> STEEL_BLOCK_ITEM =
            ITEMS.registerSimpleBlockItem("steel_block", HLBlockRegistry.STEEL_BLOCK);

    public static final DeferredItem<BlockItem> CRUDE_METAL_BLOCK_ITEM =
            ITEMS.registerSimpleBlockItem("crude_metal_block", HLBlockRegistry.CRUDE_METAL_BLOCK);

    public static void register(IEventBus modEventBus){
        ITEMS.register(modEventBus);
    }
}
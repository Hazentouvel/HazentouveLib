package net.hazen.hazentouvelib.Registries;

import net.hazen.hazentouvelib.HazentouveLib;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class HLCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, HazentouveLib.MOD_ID);

    public static final Supplier<CreativeModeTab> HL_ITEMS = CREATIVE_MODE_TABS.register("hazentouvelib_items",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(HLItemRegistry.STEEL_INGOT.get()))
                    .title(Component.translatable("creativetab.hazentouvelib.items"))
                    .withTabsBefore(CreativeModeTabs.INGREDIENTS)
                    .withTabsAfter(Identifier.fromNamespaceAndPath(HazentouveLib.MOD_ID, "hazentouvelib_blocks"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(HLItemRegistry.STEEL_INGOT.get());
                        output.accept(HLItemRegistry.STEEL_NUGGET.get());
                        output.accept(HLItemRegistry.CRUDE_METAL.get());


                    }).build());

    public static final Supplier<CreativeModeTab> HL_BLOCKS = CREATIVE_MODE_TABS.register("hazentouvelib_blocks",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(HLBlockRegistry.STEEL_BLOCK.get()))
                    .title(Component.translatable("creativetab.hazentouvelib.blocks"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(HLBlockRegistry.STEEL_BLOCK.get());
                        output.accept(HLBlockRegistry.CRUDE_METAL_BLOCK.get());


                    }).build());



    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
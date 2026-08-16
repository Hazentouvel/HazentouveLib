package net.hazen.hazentouvelib.Registries;

import net.hazen.hazentouvelib.HazentouveLib;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class HLCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, HazentouveLib.MOD_ID);

    public static final Supplier<CreativeModeTab> HAZENTOUVELIB_MATERIALS = CREATIVE_MODE_TAB.register("hazentouvelib_materials",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(HLItemRegistry.COSMIC_UPGRADE_ORB.get()))
                    .title(Component.translatable("creativetab.hazentouvelib.hazentouvelib_materials"))
                    .displayItems((itemDisplayParameters, output) -> {
                        /*
                        *** Upgrade Orbs
                         */
                        output.accept(HLItemRegistry.RADIANCE_UPGRADE_ORB.get());
                        output.accept(HLItemRegistry.SHADOW_UPGRADE_ORB.get());
                        output.accept(HLItemRegistry.COSMIC_UPGRADE_ORB.get());

                        //Runes
                        output.accept(HLItemRegistry.SHADOW_RUNE.get());
                        output.accept(HLItemRegistry.RADIANCE_RUNE.get());
                        output.accept(HLItemRegistry.COSMIC_RUNE.get());

                        //Misc
                        output.accept(HLItemRegistry.SOUL_IGNITER.get());




                    }).build());



    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }

}

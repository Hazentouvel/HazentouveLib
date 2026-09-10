package net.hazen.hazentouvelib;

import net.hazen.hazentouvelib.Registries.HLBlockRegistry;
import net.hazen.hazentouvelib.Registries.HLCreativeModeTabs;
import net.hazen.hazentouvelib.Registries.HLItemRegistry;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(HazentouveLib.MOD_ID)
public class HazentouveLib {
    public static final String MOD_ID = "hazentouvelib";
    public static final Logger LOGGER = LogUtils.getLogger();
    public HazentouveLib(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        NeoForge.EVENT_BUS.register(this);

        HLItemRegistry.register(modEventBus);
        HLCreativeModeTabs.register(modEventBus);
        HLBlockRegistry.register(modEventBus);

        modEventBus.addListener(this::addCreative);

        modContainer.registerConfig(ModConfig.Type.COMMON, HazentouveLibConfig.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(HLItemRegistry.STEEL_INGOT.get());
            event.accept(HLItemRegistry.STEEL_NUGGET.get());
            event.accept(HLItemRegistry.CRUDE_METAL.get());
        }
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(HLBlockRegistry.STEEL_BLOCK.get());
            event.accept(HLBlockRegistry.CRUDE_METAL_BLOCK.get());
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }
}

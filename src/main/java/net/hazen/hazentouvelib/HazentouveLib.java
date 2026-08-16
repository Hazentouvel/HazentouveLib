package net.hazen.hazentouvelib;

import net.hazen.hazentouvelib.Data.HLDataAttachments;
import net.hazen.hazentouvelib.Entities.HnSEntityRegistry;
import net.hazen.hazentouvelib.Registries.*;
import net.hazen.hazentouvelib.Spells.HLSpellRegistries;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(HazentouveLib.MOD_ID)
public class HazentouveLib {
    public static final String MOD_ID = "hazentouvelib";
    public static final Logger LOGGER = LogUtils.getLogger();

    public HazentouveLib(IEventBus modEventBus, ModContainer modContainer) {


        HLCreativeModeTabs.register(modEventBus);
        HLItemRegistry.register(modEventBus);
        HLDataAttachments.register(modEventBus);
        HLBlockRegistry.register(modEventBus);
        HLEffects.register(modEventBus);
        HLParticleRegistry.register(modEventBus);
        HLSounds.register(modEventBus);

        HLSpellRegistries.register(modEventBus);
        HnSEntityRegistry.register(modEventBus);

        HLAttributeRegistry.register(modEventBus);
        HLSchoolRegistry.register(modEventBus);


        modEventBus.addListener(this::commonSetup);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    @EventBusSubscriber(modid = MOD_ID, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
        }
    }

    public static ResourceLocation id(@NotNull String path)
    {
        return ResourceLocation.fromNamespaceAndPath(HazentouveLib.MOD_ID, path);
    }
}

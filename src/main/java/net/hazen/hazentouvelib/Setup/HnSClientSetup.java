package net.hazen.hazentouvelib.Setup;

import io.redspace.ironsspellbooks.entity.mobs.keeper.KeeperRenderer;
import io.redspace.ironsspellbooks.entity.spells.fiery_dagger.FieryDaggerRenderer;
import net.hazen.hazentouvelib.Entities.HnSEntityRegistry;
import net.hazen.hazentouvelib.Entities.SoulFlameBolt.SoulflameBoltRenderer;
import net.hazen.hazentouvelib.HazentouveLib;
import net.hazen.hazentouvelib.Particle.HLGenericParticle;
import net.hazen.hazentouvelib.Registries.HLParticleRegistry;
import net.minecraft.client.renderer.entity.NoopRenderer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterClientTooltipComponentFactoriesEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

@EventBusSubscriber(modid = HazentouveLib.MOD_ID)
public class HnSClientSetup {

    @SubscribeEvent
    public static void registerRenderer(EntityRenderersEvent.RegisterRenderers event) {

        event.registerEntityRenderer(HnSEntityRegistry.SOUL_FLAME_BOLT.get(), SoulflameBoltRenderer::new);
    }

    @SubscribeEvent
    public static void registerParticles(RegisterParticleProvidersEvent event)
    {

        event.registerSpriteSet(HLParticleRegistry.ENDER_EMBER_PARTICLE.get(), HLGenericParticle.Provider::new);
        event.registerSpriteSet(HLParticleRegistry.ENDER_EXPLOSION_PARTICLE.get(), HLGenericParticle.Provider::new);
        event.registerSpriteSet(HLParticleRegistry.SOUL_FLAME_EXPLOSION_PARTICLE.get(), HLGenericParticle.Provider::new);

    }
}
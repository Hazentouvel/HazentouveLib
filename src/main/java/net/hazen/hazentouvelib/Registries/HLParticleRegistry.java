package net.hazen.hazentouvelib.Registries;

import net.hazen.hazentouvelib.HazentouveLib;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class HLParticleRegistry {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(Registries.PARTICLE_TYPE, HazentouveLib.MOD_ID);

    // Ender Ember Particle
    public static final Supplier<SimpleParticleType> ENDER_EMBER_PARTICLE = PARTICLE_TYPES.register("ender_ember_particle",
            () -> new SimpleParticleType(false));
    // Ender Explosion Particle
    public static final Supplier<SimpleParticleType> ENDER_EXPLOSION_PARTICLE = PARTICLE_TYPES.register("ender_explosion_particle",
            () -> new SimpleParticleType(false));
    // Soul Fire Explosion Particle
    public static final Supplier<SimpleParticleType> SOUL_FLAME_EXPLOSION_PARTICLE = PARTICLE_TYPES.register("soul_flame_explosion_particle",
            () -> new SimpleParticleType(false));


    public static void register(IEventBus eventBus)
    {
        PARTICLE_TYPES.register(eventBus);
    }
}
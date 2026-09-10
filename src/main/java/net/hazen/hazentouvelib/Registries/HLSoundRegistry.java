package net.hazen.hazentouvelib.Registries;

import net.hazen.hazentouvelib.HazentouveLib;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class HLSoundRegistry {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, HazentouveLib.MOD_ID);

    public static final Supplier<SoundEvent> SOUL_FIRE_HURT = SOUND_EVENTS.register("soul_fire_hurt",
            () -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath(HazentouveLib.MOD_ID, "soul_fire_hurt")));
    public static final Supplier<SoundEvent> SOUL_FIRE_AMBIENT = SOUND_EVENTS.register("soul_fire_ambient",
            () -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath(HazentouveLib.MOD_ID, "soul_fire_ambient")));
    public static final Supplier<SoundEvent> SOUL_FIRE_IGNITE = SOUND_EVENTS.register("soul_fire_ignite",
            () -> SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath(HazentouveLib.MOD_ID, "soul_fire_ignite")));


    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
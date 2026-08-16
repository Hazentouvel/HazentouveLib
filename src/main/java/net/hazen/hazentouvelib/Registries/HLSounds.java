package net.hazen.hazentouvelib.Registries;

import net.hazen.hazentouvelib.HazentouveLib;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.util.DeferredSoundType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class HLSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, HazentouveLib.MOD_ID);

    /*
     * Spell Sounds
     */

    // Cosmic Casting
    public static DeferredHolder<SoundEvent, SoundEvent> COSMIC_CAST_INSTANT = registerSoundEvent("cosmic_cast_instant");
    public static DeferredHolder<SoundEvent, SoundEvent> COSMIC_CAST_LONG = registerSoundEvent("cosmic_cast_long");


    public static DeferredHolder<SoundEvent, SoundEvent> SOUL_FIRE_AMBIENT = registerSoundEvent("soul_fire_ambient");
    public static DeferredHolder<SoundEvent, SoundEvent> SOUL_FIRE_HURT = registerSoundEvent("soul_fire_hurt");
    public static DeferredHolder<SoundEvent, SoundEvent> SOUL_FIRE_IGNITE = registerSoundEvent("soul_fire_ignite");


    private static DeferredHolder<SoundEvent, SoundEvent> registerSoundEvent(String name)
    {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent
                (ResourceLocation.fromNamespaceAndPath(HazentouveLib.MOD_ID, name)));
    }


    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}

package net.hazen.hazentouvelib.Entities;


import net.hazen.hazentouvelib.Entities.SoulFlameBolt.SoulflameBolt;
import net.hazen.hazentouvelib.HazentouveLib;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class HnSEntityRegistry {
    private static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister
            .create(Registries.ENTITY_TYPE, HazentouveLib.MOD_ID);


    // Soulflame Bolt
    public static final DeferredHolder<EntityType<?>, EntityType<SoulflameBolt>> SOUL_FLAME_BOLT =
            ENTITIES.register("soul_flame_bolt", () -> EntityType.Builder.<SoulflameBolt>of(SoulflameBolt::new, MobCategory.MISC)
                    .sized(.5f, .5f)
                    .clientTrackingRange(64)
                    .build(ResourceLocation.fromNamespaceAndPath(HazentouveLib.MOD_ID, "soul_flame_bolt").toString()));

    public static void register(IEventBus eventBus)
    {
        ENTITIES.register(eventBus);
    }
}
package net.hazen.hazentouvelib.Registries;

import net.hazen.hazentouvelib.Entities.SoulFlameBolt;
import net.hazen.hazentouvelib.HazentouveLib;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class HLEntityRegistry {
    private static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister
            .create(Registries.ENTITY_TYPE, HazentouveLib.MOD_ID);

    // Spark
    public static final DeferredHolder<EntityType<?>, EntityType<SoulFlameBolt>> SOUL_FLAME_BOLT =
            ENTITIES.register("soul_flame_bolt", () -> EntityType.Builder.<SoulFlameBolt>of(SoulFlameBolt::new, MobCategory.MISC)
                    .sized(1f, 1f)
                    .clientTrackingRange(4)
                    .build(ResourceLocation.fromNamespaceAndPath(HazentouveLib.MOD_ID, "soul_flame_bolt").toString())
            );

    public static void register(IEventBus eventBus)
    {
        ENTITIES.register(eventBus);
    }
}
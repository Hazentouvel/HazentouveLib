package net.hazen.hazentouvelib.Registries;

import net.hazen.hazentouvelib.HazentouveLib;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public final class HLDamageTypes {
    public static final ResourceKey<DamageType> SOUL_FIRE = ResourceKey.create(Registries.DAMAGE_TYPE, HazentouveLib.id("soul_fire"));



    public static DamageSource soulFire(Level world) {
        return new SoulFireDamageSource(world, null);
    }

    public static DamageSource soulFire(Level world, @Nullable LivingEntity attacker) {
        return new SoulFireDamageSource(world, attacker);
    }

    public static class SoulFireDamageSource extends DamageSource {

        public SoulFireDamageSource(Level world, @Nullable LivingEntity attacker) {
            super(world.damageSources().damageTypes.getHolderOrThrow(SOUL_FIRE), attacker);
        }
    }
}
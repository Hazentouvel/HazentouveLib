package net.hazen.hazentouvelib.Setup;

import io.redspace.ironsspellbooks.api.events.SpellPreCastEvent;
import io.redspace.ironsspellbooks.damage.DamageSources;
import io.redspace.ironsspellbooks.entity.mobs.abstract_spell_casting_mob.AbstractSpellCastingMob;
import net.hazen.hazentouvelib.Registries.HLDamageTypes;
import net.hazen.hazentouvelib.Registries.HLEffects;
import net.hazen.hazentouvelib.Blocks.SoulFire.SoulFireData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent;


@EventBusSubscriber
public class HLServerEvents {

    @SubscribeEvent
    public static void onPlayerCastEvent(SpellPreCastEvent event)
    {
        var entity = event.getEntity();

        // Only run on server
        if (entity.level().isClientSide()) return;

        // Hexed check
        if (entity.hasEffect(HLEffects.HEXED))
        {
            float percentDamage = 0.25F;

            float maxHealth = entity.getMaxHealth();
            float damage = Math.max(1.0F, maxHealth * percentDamage);

            DamageSource damageSource = new DamageSource(DamageSources.getHolderFromResource(entity, HLDamageTypes.CORRUPT_MAGIC));

            entity.hurt(damageSource, damage);

            // Only play sound if it's a player (optional)
            if (entity instanceof ServerPlayer player) {
                player.level().playSound(
                        null, player.getX(), player.getY(), player.getZ(),
                        SoundEvents.SOUL_ESCAPE,
                        SoundSource.PLAYERS, 0.5f, 1f
                );
            }
        }
    }


    @SubscribeEvent
    public static void onSpellcasterMobTick(EntityTickEvent.Post event) {
        if (!(event.getEntity() instanceof AbstractSpellCastingMob mob)) {
            return;
        }

        if (mob.level().isClientSide()) {
            return;
        }

        if (!mob.isCasting()) {
            return;
        }

        // Run only once near the start of the cast
        if (mob.getMagicData().getCastDurationRemaining()
                != mob.getMagicData().getCastDuration()) {
            return;
        }

        if (mob.hasEffect(HLEffects.HEXED)) {
            float damage = Math.max(1.0F, mob.getMaxHealth() * 0.25F);

            DamageSource damageSource = new DamageSource(
                    DamageSources.getHolderFromResource(mob, HLDamageTypes.CORRUPT_MAGIC)
            );

            mob.hurt(damageSource, damage);

            mob.level().playSound(
                    null,
                    mob.getX(),
                    mob.getY(),
                    mob.getZ(),
                    SoundEvents.SOUL_ESCAPE,
                    SoundSource.HOSTILE,
                    0.5f,
                    1.0f
            );
        }
    }

    @SubscribeEvent
    public static void onEntityTick(EntityTickEvent.Post event) {
        if (!(event.getEntity() instanceof LivingEntity living)) {
                    return;
        }

        if (living.level().isClientSide()) {
                    SoulFireData.clientTick(living);
        } else {
                    SoulFireData.serverTick(living);
        }
    }
}
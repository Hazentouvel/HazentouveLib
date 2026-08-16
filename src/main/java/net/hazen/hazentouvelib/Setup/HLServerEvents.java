package net.hazen.hazentouvelib.Setup;

import io.redspace.ironsspellbooks.api.entity.IMagicEntity;
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
        if (entity.level().isClientSide()) return;

        if (entity.hasEffect(HLEffects.HEXED))
        {
            float percentDamage = 0.15F;

            float maxHealth = entity.getMaxHealth();
            float damage = Math.max(1.0F, maxHealth * percentDamage);

            DamageSource damageSource = new DamageSource(DamageSources.getHolderFromResource(entity, HLDamageTypes.CORRUPT_MAGIC));

            entity.hurt(damageSource, damage);

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
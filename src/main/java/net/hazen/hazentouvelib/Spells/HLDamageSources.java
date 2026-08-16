package net.hazen.hazentouvelib.Spells;

import net.hazen.hazentouvelib.Blocks.SoulFire.SoulFireData;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

@EventBusSubscriber
public class HLDamageSources {

    @SubscribeEvent
    public static void postHitEffects(LivingDamageEvent.Post event) {

        var damageSource = event.getSource();

        if (damageSource instanceof HLSpellDamageSource spellDamageSource
                && spellDamageSource.hasPostHitEffects()) {

            var target = event.getEntity();

            if (spellDamageSource.getSoulFireTime() > 0) {

                SoulFireData.addSoulFireTicks(target, spellDamageSource.getSoulFireTime());
            }
        }
    }
}
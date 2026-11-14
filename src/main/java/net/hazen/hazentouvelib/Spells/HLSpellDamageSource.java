package net.hazen.hazentouvelib.Spells;

import io.redspace.ironsspellbooks.api.spells.AbstractSpell;
import io.redspace.ironsspellbooks.damage.SpellDamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HLSpellDamageSource extends SpellDamageSource {

    AbstractSpell spell;
    int soulFireTime;
    int enderFireTime;

    private HLSpellDamageSource(@NotNull Entity directEntity, @NotNull Entity causingEntity, @Nullable Vec3 damageSourcePosition, AbstractSpell spell) {
        super(directEntity, causingEntity, damageSourcePosition, spell);
        this.spell = spell;
    }

    public static HLSpellDamageSource source(@NotNull Entity directEntity, @NotNull Entity causingEntity, @NotNull AbstractSpell spell) {
        return new HLSpellDamageSource(directEntity, causingEntity, null, spell);
    }

    public HLSpellDamageSource setSoulFireTicks(int soulFireTicks) {
        this.soulFireTime = soulFireTicks;
        return this;
    }

    public HLSpellDamageSource setEnderFireTicks(int enderFireTicks) {
        this.enderFireTime = enderFireTicks;
        return this;
    }

    public int getSoulFireTime() {
        return this.soulFireTime;
    }

    public int getEnderFireTime() {
        return this.enderFireTime;
    }

    @Override
    public boolean hasPostHitEffects() {
        return this.getSoulFireTime() > 0 || this.getEnderFireTime() > 0;
    }
}
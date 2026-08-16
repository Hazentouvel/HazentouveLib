package net.hazen.hazentouvelib.Blocks.SoulFire;

import com.mojang.serialization.Codec;
import net.hazen.hazentouvelib.Datagen.HLTags;
import net.hazen.hazentouvelib.Registries.HLDamageTypes;
import net.hazen.hazentouvelib.Setup.HLAttachmentUtil;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.minecraft.core.Holder;
import java.util.Optional;
import net.minecraft.core.registries.Registries;

public class SoulFireData {
    public static final float BASE_PERCENT_DAMAGE = 0.01F;
    public static final float FIRE_RESISTANCE_DAMAGE_RESISTANCE = 0.25F;
    public static final float FIRE_PROT_DAMAGE_RESISTANCE = 0.05F;

    public static Optional<Holder<Enchantment>> getEntry(
            HolderLookup.Provider lookup,
            ResourceKey<Enchantment> key
    ) {
        if (lookup == null)
            return Optional.empty();

        return lookup.lookup(Registries.ENCHANTMENT)
                .flatMap(registry -> registry.get(key));
    }

    public static int getEquipmentLevel(
            HolderLookup.Provider lookup,
            ResourceKey<Enchantment> key,
            LivingEntity entity
    ) {
        if (lookup == null)
            return 0;

        return getEntry(lookup, key)
                .map(e -> EnchantmentHelper.getEnchantmentLevel(e, entity))
                .orElse(0);
    }

    public static final AttachmentType<Long> ATTACHMENT =
        AttachmentType.builder(() -> 0L)
                      .serialize(Codec.LONG)
                      .build();

    private static void sync(LivingEntity entity) {
        HLAttachmentUtil.syncToTracking(
            new Payload(entity.getId(), entity.getData(ATTACHMENT)), entity.level(), entity.blockPosition());
    }

    public static void setSoulFireTicks(LivingEntity entity, long ticks) {
        entity.setData(ATTACHMENT, ticks);
        try {
            entity.setRemainingFireTicks((int) ticks);
        } catch (NoSuchMethodError ignored) {
        }
        sync(entity);
    }

    public static void addSoulFireTicks(LivingEntity entity, int ticks) {
        int i = getEquipmentLevel(
            entity.level()
                  .registryAccess(), Enchantments.FIRE_PROTECTION, entity
        );
        if (i > 0) {
            ticks -= Mth.floor(ticks * i * 0.15F);
        }

        entity.setData(ATTACHMENT, entity.getData(ATTACHMENT) + ticks);
        try {
            entity.setRemainingFireTicks(entity.getData(ATTACHMENT).intValue());
        } catch (NoSuchMethodError ignored) {
        }
        sync(entity);
    }

    public static boolean isOnSoulFire(LivingEntity entity) {
        return entity.getData(ATTACHMENT) > 0;
    }

    public static boolean putOut(LivingEntity entity) {
        if (entity.getData(ATTACHMENT) == 0)
            return false;

        entity.setData(ATTACHMENT, 0L);
        try { entity.setRemainingFireTicks(0); } catch (NoSuchMethodError ignored) {}
        sync(entity);
        return true;
    }

    public static final float SOUL_FIRE_DAMAGE = 2.0F;
    public static final int SOUL_FIRE_DAMAGE_INTERVAL = 10;

    public static void serverTick(LivingEntity entity) {
        long soulFireTicks = entity.getData(ATTACHMENT);

        if (soulFireTicks <= 0)
            return;
        if (entity.getType().is(HLTags.SOUL_FIRE_IMMUNE)) {
            entity.setData(ATTACHMENT, 0L);
            try { entity.setRemainingFireTicks(0); } catch (NoSuchMethodError ignored) {}
            sync(entity);
            return;
        }
        if (entity.tickCount % SOUL_FIRE_DAMAGE_INTERVAL == 0) {
            float damage = Math.max(1.0F, entity.getMaxHealth() * getDamageHealthScaling(entity));
            if (damage > 0.0F) {
                entity.hurt(HLDamageTypes.soulFire(entity.level()), damage);
            }
        }
        soulFireTicks -= entity.getFluidHeight(FluidTags.WATER) > 0 ? 3 : 1;

        entity.setData(ATTACHMENT, Math.max(soulFireTicks, 0L));

        try {
            entity.setRemainingFireTicks(entity.getData(ATTACHMENT).intValue());
        } catch (NoSuchMethodError ignored) {}

        if (soulFireTicks <= 0) {
            sync(entity);
        }
    }
    public static float getDamageHealthScaling(LivingEntity entity) {

        return BASE_PERCENT_DAMAGE * getDamagePenalties(entity) * getDamageBonuses(entity);
    }

    public static float getDamagePenalties(LivingEntity entity) {
        float fireProt = Math.min(
            FIRE_PROT_DAMAGE_RESISTANCE * getEquipmentLevel(
                entity.level()
                      .registryAccess(), Enchantments.FIRE_PROTECTION, entity
            ), 0.5F
        );
        int fireResLevel = Optional.ofNullable(entity.getEffect(MobEffects.FIRE_RESISTANCE))
                                   .map(MobEffectInstance::getAmplifier)
                                   .orElse(-1) + 1;
        float fireRes = 0;

        if (fireResLevel > 0)
            fireRes = FIRE_RESISTANCE_DAMAGE_RESISTANCE;

        for (int i = 1; i < fireResLevel; i++) {
            fireRes += (float) (0.05 * (i) + (0.25F * Math.pow(0.5F, i)));
        }

        float immunityReduction = entity.fireImmune() ? 0.25F : 0;

        return Math.max(1 - (fireRes + fireProt + immunityReduction), 0.10F);
    }

    public static float getDamageBonuses(LivingEntity entity) {
        return 1F;
    }

    @OnlyIn(Dist.CLIENT)
    public static void clientTick(LivingEntity entity) {
        var soulFireTicks = entity.getData(ATTACHMENT);

        if (soulFireTicks > 0) {

            double fluidHeight = entity.getFluidHeight(FluidTags.WATER);
            if (fluidHeight > 0) {

                Level world = entity.level();
                RandomSource random = world.random;
                Vec3 pos = entity.position();

                for (int i = 0; i < 2; i++) {
                    world.addParticle(
                        ParticleTypes.BUBBLE_POP, entity.getRandomX(1),
                        pos.y() + Math.min(fluidHeight, entity.getBbHeight()) * random.nextFloat(),
                        entity.getRandomZ(1), 0.0, 0.04, 0.0
                    );
                    world.addParticle(
                        ParticleTypes.SMOKE, entity.getRandomX(1),
                        pos.y() + Math.min(fluidHeight, entity.getBbHeight()) * random.nextFloat(),
                        entity.getRandomZ(1), 0.0, 0.04, 0.0
                    );
                }
                if (world.random.nextInt(12) == 0) {
                    entity.playSound(
                        SoundEvents.FIRE_EXTINGUISH, 0.2F + random.nextFloat() * 0.2F,
                        0.9F + random.nextFloat() * 0.15F
                    );
                }
            }
        }
    }

    public record Payload(int entityId, long burnTicks) implements CustomPacketPayload {

        public static final StreamCodec<FriendlyByteBuf, Payload> CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, Payload::entityId,
            ByteBufCodecs.VAR_LONG, Payload::burnTicks,
            Payload::new
        );

        public static final CustomPacketPayload.Type<Payload> TYPE = HLAttachmentUtil.create("soul_fire");

        public static void execute(Payload payload, IPayloadContext context) {
            var level = context.player()
                               .level();
            Optional.ofNullable(level.getEntity(payload.entityId))
                    .ifPresent(e -> e.setData(ATTACHMENT, payload.burnTicks));
        }

        @Override
        public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
            return TYPE;
        }
    }

    public static long getSoulFireTicks(LivingEntity entity) {
        return entity.getData(ATTACHMENT);
    }
}
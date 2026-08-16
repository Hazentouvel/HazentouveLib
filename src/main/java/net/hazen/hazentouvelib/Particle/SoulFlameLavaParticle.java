package net.hazen.hazentouvelib.Particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SoulFlameLavaParticle extends TextureSheetParticle {
    protected SoulFlameLavaParticle(ClientLevel level, double x, double y, double z) {
        super(level, x, y, z, (double)0.0F, (double)0.0F, (double)0.0F);
        this.gravity = 0.75F;
        this.friction = 0.999F;
        this.xd *= (double)0.8F;
        this.yd *= (double)0.8F;
        this.zd *= (double)0.8F;
        this.yd = (double)(this.random.nextFloat() * 0.4F + 0.05F);
        this.quadSize *= this.random.nextFloat() * 2.0F + 0.2F;
        this.lifetime = (int)((double)16.0F / (Math.random() * 0.8 + 0.2));
    }

    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    public int getLightColor(float partialTick) {
        int i = super.getLightColor(partialTick);
        int j = 240;
        int k = i >> 16 & 255;
        return 240 | k << 16;
    }

    public float getQuadSize(float scaleFactor) {
        float f = ((float)this.age + scaleFactor) / (float)this.lifetime;
        return this.quadSize * (1.0F - f * f);
    }

    public void tick() {
        super.tick();
        if (!this.removed) {
            float f = (float)this.age / (float)this.lifetime;
            if (this.random.nextFloat() > f) {
                this.level.addParticle(ParticleTypes.SMOKE, this.x, this.y, this.z, this.xd, this.yd, this.zd);
            }
        }

    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public Provider(SpriteSet sprites) {
            this.sprite = sprites;
        }

        public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            SoulFlameLavaParticle soulFlameLavaParticle = new SoulFlameLavaParticle(level, x, y, z);
            soulFlameLavaParticle.pickSprite(this.sprite);
            return soulFlameLavaParticle;
        }
    }
}

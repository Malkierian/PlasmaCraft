package net.minecraft.src.Plasmacraft;

import net.minecraft.src.EntityFX;
import net.minecraft.src.Tessellator;
import net.minecraft.src.World;

public class EntityAcidFX extends EntityFX
{
    private float ease_out;
    private float lavaParticleScale;
    private int lifetime;

    public EntityAcidFX(World world, double d, double d1, double d2)
    {
        super(world, d, d1, d2, 0.0D, 0.0D, 0.0D);
        motionX = 0.0D;
        motionY = rand.nextFloat() * 0.1F + 0.05F;
        motionZ = 0.0D;
        particleRed = 0.0F;
        particleBlue = 0.2F;
        particleGreen = 1.0F;
        particleScale *= rand.nextFloat() * 2.0F + 0.2F;
        lavaParticleScale = particleScale;
        particleMaxAge = (int)(32D / (Math.random() * 0.80000000000000004D + 0.20000000000000001D));
        noClip = false;
        super.setParticleTextureIndex(34);
        ease_out = 1.0F;
    }

    public float getEntityBrightness(float f)
    {
        return 1.0F;
    }

    public void renderParticle(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5)
    {
        float f6 = ((float)particleAge + f) / (float)particleMaxAge;
        particleScale = lavaParticleScale * (1.0F - f6 * f6);
        super.renderParticle(tessellator, f, f1, f2, f3, f4, f5);
    }

    public void onUpdate()
    {
        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;
        motionY -= 0.0050000000000000001D * (double)ease_out;
        ease_out *= 0.9F;
        lifetime++;
        if(motionY <= 0.0D || lifetime > 180)
        {
            setDead();
        }
        moveEntity(motionX, motionY, motionZ);
    }
}

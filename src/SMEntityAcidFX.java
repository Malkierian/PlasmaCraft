package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode

import java.util.Random;

public class SMEntityAcidFX extends EntityFX
{
    public SMEntityAcidFX(World world, double d, double d1, double d2)
    {
        super(world, d, d1, d2, 0.0D, 0.0D, 0.0D);
        motionX = 0.0f;
        motionY = rand.nextFloat() * 0.1F + 0.05F;
        motionZ = 0.0f;
        particleRed = 0.0f;
        particleBlue = 0.2f;
        particleGreen = 1.0F;
        particleScale *= rand.nextFloat() * 2.0F + 0.2F;
        lavaParticleScale = particleScale;
        particleMaxAge = (int)(32D / (Math.random() * 0.8D + 0.2D));
        noClip = false;
        particleTextureIndex = 34;
        ease_out = 1.0f;
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
        motionY -= 0.005D * ease_out;
        ease_out *= 0.9f;
        lifetime++;
        if(motionY <= 0.0f || lifetime > 180)
        {
			setEntityDead();
		}

        moveEntity(motionX, motionY, motionZ);
    }

	private float ease_out;
    private float lavaParticleScale;
    private int lifetime;
}

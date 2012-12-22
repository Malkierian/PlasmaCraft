package com.elvenwater.malkierian.Plasmacraft.client;

import net.minecraft.client.renderer.RenderEngine;

import org.lwjgl.opengl.GL11;

import com.elvenwater.malkierian.Plasmacraft.common.CommonProxy;

import cpw.mods.fml.client.FMLTextureFX;

public class TextureTintedFlowFX extends FMLTextureFX
{
    protected float field_1138_g[];
    protected float field_1137_h[];
    protected float field_1136_i[];
    protected float field_1135_j[];
    private int tickCounter = 0;
    private float rTint;
    private float gTint;
    private float bTint;
    private float aTint;
    private float rBase;
    private float gBase;
    private float bBase;
    private float aBase;
    private float rSpread;
    private float gSpread;
    private float bSpread;
    private float aSpread;

    public TextureTintedFlowFX(int i, float f, float f1, float f2, float f3, float f4, float f5, 
            float f6, float f7, float f8, float f9, float f10, float f11)
    {
        super(i);
    	imageData = new byte[tileSizeSquare * 4];
        field_1138_g = new float[tileSizeSquare];
        field_1137_h = new float[tileSizeSquare];
        field_1136_i = new float[tileSizeSquare];
        field_1135_j = new float[tileSizeSquare];
        tickCounter = 0;
        tileSize = 1;
        rTint = f;
        gTint = f1;
        bTint = f2;
        aTint = f3;
        rBase = f4;
        gBase = f5;
        bBase = f6;
        aBase = f7;
        rSpread = f8;
        gSpread = f9;
        bSpread = f10;
        aSpread = f11;
    }

    public void onTick()
    {
        this.tickCounter++;
        for(int i = 0; i < tileSizeBase; i++)
        {
            for(int k = 0; k < tileSizeBase; k++)
            {
                float f = 0.0F;
                for(int j1 = k - 2; j1 <= k; j1++)
                {
                    int k1 = i & tileSizeMask;
                    int i2 = j1 & tileSizeMask;
                    f += field_1138_g[k1 + i2 * tileSizeBase];
                }

                field_1137_h[i + k * tileSizeBase] = f / 3.2F + field_1136_i[i + k * tileSizeBase] * 0.8F;
            }

        }

        for(int j = 0; j < tileSizeBase; j++)
        {
            for(int l = 0; l < tileSizeBase; l++)
            {
                field_1136_i[j + l * tileSizeBase] += field_1135_j[j + l * tileSizeBase] * 0.05F;
                if(field_1136_i[j + l * tileSizeBase] < 0.0F)
                {
                    field_1136_i[j + l * tileSizeBase] = 0.0F;
                }
                field_1135_j[j + l * tileSizeBase] -= 0.3F;
                if(Math.random() < 0.20000000000000001D)
                {
                    field_1135_j[j + l * tileSizeBase] = 0.5F;
                }
            }

        }

        float af[] = field_1137_h;
        field_1137_h = field_1138_g;
        field_1138_g = af;
        for(int i1 = 0; i1 < tileSizeSquare; i1++)
        {
            float f1 = field_1138_g[i1 - tickCounter * tileSizeBase & tileSizeSquareMask];
            if(f1 > 1.0F)
            {
                f1 = 1.0F;
            }
            if(f1 < 0.0F)
            {
                f1 = 0.0F;
            }
            float f2 = f1 * f1;
            int l1 = (int)(rBase + f2 * rSpread);
            int j2 = (int)(gBase + f2 * gSpread);
            int k2 = (int)(bBase + f2 * bSpread);
            int l2 = (int)(aBase + f2 * aSpread);
            if(anaglyphEnabled)
            {
                int i3 = (l1 * 30 + j2 * 59 + k2 * 11) / 100;
                int j3 = (l1 * 30 + j2 * 70) / 100;
                int k3 = (l1 * 30 + k2 * 70) / 100;
                l1 = i3;
                j2 = j3;
                k2 = k3;
            }
            l1 = (int)((float)l1 * rTint);
            j2 = (int)((float)j2 * gTint);
            k2 = (int)((float)k2 * bTint);
            l2 = (int)((float)l2 * aTint);
            if(l1 < 0)
            {
                l1 = 0;
            }
            if(j2 < 0)
            {
                j2 = 0;
            }
            if(k2 < 0)
            {
                k2 = 0;
            }
            if(l2 < 0)
            {
                l2 = 0;
            }
            if(l1 > 255)
            {
                l1 = 255;
            }
            if(j2 > 255)
            {
                j2 = 255;
            }
            if(k2 > 255)
            {
                k2 = 255;
            }
            if(l2 > 255)
            {
                l2 = 255;
            }
            imageData[i1 * 4 + 0] = (byte)l1;
            imageData[i1 * 4 + 1] = (byte)j2;
            imageData[i1 * 4 + 2] = (byte)k2;
            imageData[i1 * 4 + 3] = (byte)l2;
        }

    }

    public void bindImage(RenderEngine renderengine)
    {
    	GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/ /* GL_TEXTURE_2D */,
    			renderengine.getTexture(CommonProxy.BLOCK_PNG));
    }
}

package com.elvenwater.malkierian.Plasmacraft.client;

import net.minecraft.client.renderer.RenderEngine;
import net.minecraftforge.client.ForgeHooksClient;

import com.elvenwater.malkierian.Plasmacraft.common.CommonProxy;

import cpw.mods.fml.client.FMLTextureFX;

public class TextureTintedStillFX extends FMLTextureFX
{
    protected float field_1158_g[];
    protected float field_1157_h[];
    protected float field_1156_i[];
    protected float field_1155_j[];
    private int tickCounter;
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

    public TextureTintedStillFX(int i, float f, float f1, float f2, float f3, float f4, float f5, 
            float f6, float f7, float f8, float f9, float f10, float f11)
    {
        super(i);
    	imageData = new byte[tileSizeSquare * 4];
        field_1158_g = new float[tileSizeSquare];
        field_1157_h = new float[tileSizeSquare];
        field_1156_i = new float[tileSizeSquare];
        field_1155_j = new float[tileSizeSquare];
        tickCounter = 0;
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
        tickCounter++;
        for(int i = 0; i < tileSizeBase; i++)
        {
            for(int k = 0; k < tileSizeBase; k++)
            {
                float f = 0.0F;
                for(int j1 = i - 1; j1 <= i + 1; j1++)
                {
                    int k1 = j1 & tileSizeMask;
                    int i2 = k & tileSizeMask;
                    f += field_1158_g[k1 + i2 * tileSizeBase];
                }

                field_1157_h[i + k * tileSizeBase] = f / 3.3F + field_1156_i[i + k * tileSizeBase] * 0.8F;
            }

        }

        for(int j = 0; j < tileSizeBase; j++)
        {
            for(int l = 0; l < tileSizeBase; l++)
            {
                field_1156_i[j + l * tileSizeBase] += field_1155_j[j + l * tileSizeBase] * 0.05F;
                if(field_1156_i[j + l * tileSizeBase] < 0.0F)
                {
                    field_1156_i[j + l * tileSizeBase] = 0.0F;
                }
                field_1155_j[j + l * tileSizeBase] -= 0.1F;
                if(Math.random() < 0.050000000000000003D)
                {
                    field_1155_j[j + l * tileSizeBase] = 0.5F;
                }
            }

        }

        float af[] = field_1157_h;
        field_1157_h = field_1158_g;
        field_1158_g = af;
        for(int i1 = 0; i1 < tileSizeSquare; i1++)
        {
            float f1 = field_1158_g[i1];
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

    @Override
    public void bindImage(RenderEngine renderengine)
    {
    	ForgeHooksClient.bindTexture(CommonProxy.BLOCK_PNG, 0);
    }
}

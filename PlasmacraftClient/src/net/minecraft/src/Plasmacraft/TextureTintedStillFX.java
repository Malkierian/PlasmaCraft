package net.minecraft.src.Plasmacraft;

import java.util.logging.Level;

import net.minecraft.src.ModLoader;
import net.minecraft.src.RenderEngine;
import net.minecraft.src.TextureFX;
import net.minecraft.src.forge.MinecraftForgeClient;

public class TextureTintedStillFX extends TextureFX
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

    private int int_numPixels = 256;
    private int int_size = 16;
    private int int_sizeMinus1 = 0xF;

    public TextureTintedStillFX(int i, float f, float f1, float f2, float f3, float f4, float f5, 
            float f6, float f7, float f8, float f9, float f10, float f11)
    {
        super(i);
//    	try
//    	{
//			Class<? extends Object> sizeClass = Class.forName("com.pclewis.mcpatcher.mod.TileSize");
//			int_numPixels = sizeClass.getDeclaredField("int_numPixels").getInt(sizeClass);
//			int_size = sizeClass.getDeclaredField("int_size").getInt(sizeClass);
//			int_sizeMinus1 = sizeClass.getDeclaredField("int_sizeMinus1").getInt(sizeClass);
//		}
//    	catch (Throwable t)
//    	{
//    		ModLoader.getLogger().log(Level.WARNING,"Error ");
//    	}
    	imageData = new byte[int_numPixels * 4];
        field_1158_g = new float[int_numPixels];
        field_1157_h = new float[int_numPixels];
        field_1156_i = new float[int_numPixels];
        field_1155_j = new float[int_numPixels];
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
        for(int i = 0; i < int_size; i++)
        {
            for(int k = 0; k < int_size; k++)
            {
                float f = 0.0F;
                for(int j1 = i - 1; j1 <= i + 1; j1++)
                {
                    int k1 = j1 & int_sizeMinus1;
                    int i2 = k & int_sizeMinus1;
                    f += field_1158_g[k1 + i2 * int_size];
                }

                field_1157_h[i + k * int_size] = f / 3.3F + field_1156_i[i + k * int_size] * 0.8F;
            }

        }

        for(int j = 0; j < int_size; j++)
        {
            for(int l = 0; l < int_size; l++)
            {
                field_1156_i[j + l * int_size] += field_1155_j[j + l * int_size] * 0.05F;
                if(field_1156_i[j + l * int_size] < 0.0F)
                {
                    field_1156_i[j + l * int_size] = 0.0F;
                }
                field_1155_j[j + l * int_size] -= 0.1F;
                if(Math.random() < 0.050000000000000003D)
                {
                    field_1155_j[j + l * int_size] = 0.5F;
                }
            }

        }

        float af[] = field_1157_h;
        field_1157_h = field_1158_g;
        field_1158_g = af;
        for(int i1 = 0; i1 < int_numPixels; i1++)
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
    	MinecraftForgeClient.bindTexture(PlasmaCraftCore.liquidTexture);
    }
}

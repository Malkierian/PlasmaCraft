package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.Random;

public class SMBlockPlasmaOre extends BlockOre
{

    public SMBlockPlasmaOre(int i, int j)
    {
        super(i, j);
    }

    public int idDropped(int i, Random random)
    {
        return blockID;
    }

    public int quantityDropped(Random random)
    {
    	return 1;
    }
    
    public void onEntityWalking(World world, int i,int j, int k, Entity entity)
    {
    	if (blockID == mod_PlasmaCraft.oreUranium.blockID)
    	{
    		entity.attackEntityFrom(null, 5);
    	}
    }

    public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity)
    {
    	if (blockID == mod_PlasmaCraft.oreUranium.blockID)
    	{
    			entity.attackEntityFrom(null, 5);
        }
    }
}




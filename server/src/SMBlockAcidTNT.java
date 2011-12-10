package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode

import java.util.Random;

public class SMBlockAcidTNT extends BlockTNT
{

    public SMBlockAcidTNT(int i, int j, int k, int l)
    {
        super(i, j);
        topTextureIndex = k;
        bottomTextureIndex = l;
        //setTickOnLoad(true);
    }

	/*
    public void updateTick(World world, int i, int j, int k, Random random)
    {
        if(world.getBlockId(i, j, k) != blockID)
        {
            return;
        }

        world.setBlockAndMetadata(i, j, k, mod_PlasmaCraft.causticStillBlockID, 7);
        TileEntityCaustic newTile = (TileEntityCaustic)(world.getBlockTileEntity(i, j, k));
        if(newTile != null)
        {
			newTile.setCausticID(1);
		}
        world.markBlocksDirty(i, j, k, i, j, k);
	}
	*/

    public int getBlockTextureFromSide(int i)
    {
        if(i == 0)
        {
            return bottomTextureIndex;
        }
        if(i == 1)
        {
            return topTextureIndex;
        } else
        {
            return blockIndexInTexture;
        }
    }

    public void onBlockDestroyedByExplosion(World world, int i, int j, int k)
    {
    	SMEntityAcidTNTPrimed primed = new SMEntityAcidTNTPrimed(world, (float)i + 0.5F, (float)j + 0.5F, (float)k + 0.5F);
        primed.fuse = world.rand.nextInt(primed.fuse / 4) + primed.fuse / 8;
        world.entityJoinedWorld(primed);
    }

    public void onBlockDestroyedByPlayer(World world, int i, int j, int k, int l)
    {
        if(world.singleplayerWorld)
        {
            return;
        } else
        {
        	SMEntityAcidTNTPrimed primed = new SMEntityAcidTNTPrimed(world, (float)i + 0.5F, (float)j + 0.5F, (float)k + 0.5F);
            world.entityJoinedWorld(primed);
            world.playSoundAtEntity(primed, "random.fuse", 1.0F, 1.0F);
            return;
        }
    }

	private int topTextureIndex;
    private int bottomTextureIndex;
}

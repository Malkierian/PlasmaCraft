package com.elvenwater.malkierian.Plasmacraft.common;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTNT;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockAcidTNT extends BlockTNT
{

    private int topTextureIndex;
    private int bottomTextureIndex;

    public BlockAcidTNT(int i, int j, int k, int l)
    {
        super(i, j);
        topTextureIndex = k;
        bottomTextureIndex = l;
        setHardness(0.0F);
        setStepSound(Block.soundGrassFootstep);
    }
    
    public void addCreativeItems(ArrayList itemList)
    {    	
    	itemList.add(new ItemStack(this, 1));
    }

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
        EntityAcidTNTPrimed entityacidtntprimed = new EntityAcidTNTPrimed(world, (float)i + 0.5F, (float)j + 0.5F, (float)k + 0.5F);
        entityacidtntprimed.fuse = world.rand.nextInt(entityacidtntprimed.fuse / 4) + entityacidtntprimed.fuse / 8;
        world.spawnEntityInWorld(entityacidtntprimed);
    }

    public void onBlockDestroyedByPlayer(World world, int i, int j, int k, int l)
    {
        if(world.isRemote)
        {
            return;
        } else
        {
            EntityAcidTNTPrimed entityacidtntprimed = new EntityAcidTNTPrimed(world, (float)i + 0.5F, (float)j + 0.5F, (float)k + 0.5F);
            world.spawnEntityInWorld(entityacidtntprimed);
            world.playSoundAtEntity(entityacidtntprimed, "random.fuse", 1.0F, 1.0F);
            return;
        }
    }

	@Override
	public String getTextureFile()
	{
		return CommonProxy.BLOCK_PNG;
	}
}

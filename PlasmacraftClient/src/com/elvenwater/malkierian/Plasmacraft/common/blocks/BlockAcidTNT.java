package com.elvenwater.malkierian.Plasmacraft.common.blocks;

import java.util.ArrayList;

import com.elvenwater.malkierian.Plasmacraft.common.CommonProxy;
import com.elvenwater.malkierian.Plasmacraft.common.EntityAcidTNTPrimed;

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

    @Override
    public void onBlockDestroyedByExplosion(World world, int i, int j, int k)
    {
    	if(!world.isRemote)
    	{
	        EntityAcidTNTPrimed entityacidtntprimed = new EntityAcidTNTPrimed(world, (float)i + 0.5F, (float)j + 0.5F, (float)k + 0.5F);
	        entityacidtntprimed.fuse = world.rand.nextInt(entityacidtntprimed.fuse / 4) + entityacidtntprimed.fuse / 8;
	        world.spawnEntityInWorld(entityacidtntprimed);
    	}
    }

    @Override
    public void onBlockDestroyedByPlayer(World world, int i, int j, int k, int l)
    {
        if(!world.isRemote)
        {
        	if((l & 1) == 1)
            {
        		EntityAcidTNTPrimed entityacidtntprimed = new EntityAcidTNTPrimed(world, (float)i + 0.5F, (float)j + 0.5F, (float)k + 0.5F);
	            world.spawnEntityInWorld(entityacidtntprimed);
	            world.playSoundAtEntity(entityacidtntprimed, "random.fuse", 1.0F, 1.0F);
	            return;
            }
        }
    }

	@Override
	public String getTextureFile()
	{
		return CommonProxy.BLOCK_PNG;
	}
}

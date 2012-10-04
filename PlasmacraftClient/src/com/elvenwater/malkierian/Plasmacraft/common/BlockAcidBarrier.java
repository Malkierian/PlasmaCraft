package com.elvenwater.malkierian.Plasmacraft.common;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.DamageSource;
import net.minecraft.src.Entity;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.World;

public class BlockAcidBarrier extends Block
{
    protected BlockAcidBarrier(int i, int j)
    {
        super(i, j, Material.glass);
        setHardness(3F);
        setResistance(5F);
        setLightValue(0.2F);
        setLightOpacity(2);
        setLightValue(1.0F);
        setStepSound(Block.soundGlassFootstep);
        setCreativeTab(CreativeTabs.tabBlock);
    }
    
    public void addCreativeItems(ArrayList itemList)
    {    	
    	itemList.add(new ItemStack(this, 1));
    }

    public int idDropped(int i, Random random)
    {
        return PlasmaCraft.acidBarrier.blockID;
    }

    public int getRenderBlockPass()
    {
        return 1;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public void onEntityWalking(World world, int i, int j, int k, Entity entity)
    {
        entity.attackEntityFrom(DamageSource.magic, 2);
    }

    public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity)
    {
        entity.attackEntityFrom(DamageSource.magic, 2);
    }
    
    @Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
    	float shrinkAmount = 0.125F;
    	return AxisAlignedBB.getBoundingBox(x + shrinkAmount, y + shrinkAmount, z + shrinkAmount,
    			x + 1 - shrinkAmount, y + 1 - shrinkAmount, z + 1 - shrinkAmount);
	}

	@Override
	public String getTextureFile()
	{
		return CommonProxy.BLOCK_PNG;
	}
}

package com.elvenwater.malkierian.Plasmacraft.common;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;

public class BlockReinforcedGlass extends BlockBreakable
{
    public BlockReinforcedGlass(int i, int j, Material material, boolean flag, float resistance)
    {
        super(i, j, material, flag);
        setHardness(1.0F);
        setResistance(resistance);
        setStepSound(Block.soundGlassFootstep);
        setTickRandomly(true);
    }
    
    public void addCreativeItems(ArrayList itemList)
    {    	
    	itemList.add(new ItemStack(this, 1));
    }

    public int idDropped(int i, Random random)
    {
        return blockID;
    }

    public int quantityDropped(Random random)
    {
        return 1;
    }

	@Override
	public String getTextureFile()
	{
		return CommonProxy.BLOCK_PNG;
	}
}

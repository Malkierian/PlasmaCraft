package com.elvenwater.malkierian.Plasmacraft.common.blocks;

import java.util.ArrayList;
import java.util.Random;

import com.elvenwater.malkierian.Plasmacraft.common.CommonProxy;
import com.elvenwater.malkierian.Plasmacraft.common.PlasmaCraft;

import cpw.mods.fml.common.FMLCommonHandler;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockReinforcedGlass extends BlockBreakable
{
	public BlockReinforcedGlass(int i, String filename, Material material, boolean flag, float resistance)
	{
		super(i, filename, material, flag);
		setHardness(1.0F);
		setResistance(resistance);
		setStepSound(Block.soundGlassFootstep);
		setTickRandomly(true);
		setCreativeTab(CreativeTabs.tabBlock);
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
		if(blockID == PlasmaCraft.frozenCryonite.blockID)
			return 0;
		else
			return 1;
	}
	
	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
	{
		if(blockID == PlasmaCraft.frozenCryonite.blockID)
		{
			par1World.setBlockAndMetadataWithNotify(par2, par3, par4, PlasmaCraft.cryoniteMoving.blockID, 0, 0);
		}
		else
			super.breakBlock(par1World, par2, par3, par4, par5, par6);
	}
}

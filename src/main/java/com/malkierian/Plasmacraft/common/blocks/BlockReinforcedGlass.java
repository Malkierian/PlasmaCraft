package com.malkierian.Plasmacraft.common.blocks;

import java.util.ArrayList;
import java.util.Random;

import com.malkierian.Plasmacraft.common.CommonProxy;
import com.malkierian.Plasmacraft.common.PlasmaCraft;

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
		super(filename, material, flag);
		setHardness(1.0F);
		setResistance(resistance);
		setStepSound(Block.soundTypeGlass);
		setTickRandomly(true);
		setCreativeTab(CreativeTabs.tabBlock);
	}
	
//	public void addCreativeItems(ArrayList itemList)
//	{		
//		itemList.add(new ItemStack(this, 1));
//	}

	public int quantityDropped(Random random)
	{
		if(this == PlasmaCraft.frozenCryonite)
			return 0;
		else
			return 1;
	}
	
	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6)
	{
		///TODO Re-enable liquid replacement
//		if(this == PlasmaCraft.frozenCryonite)
//		{
//			par1World.setBlock(par2, par3, par4, PlasmaCraft.cryoniteMoving);
//		}
//		else
			super.breakBlock(par1World, par2, par3, par4, par5, par6);
	}
}

package com.malkierian.plasmacraft.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.world.World;

import com.malkierian.plasmacraft.common.PlasmaCraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockReinforcedGlass extends BlockBreakable
{
	public BlockReinforcedGlass(String filename, Material material, boolean flag, float resistance)
	{
		super(filename, material, flag);
		setHardness(1.0F);
		setResistance(resistance);
		setStepSound(Block.soundTypeGlass);
		setTickRandomly(true);
		setCreativeTab(PlasmaCraft.plasmaTab);
	}

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
		if(this == PlasmaCraft.frozenCryonite)
		{
//			par1World.setBlock(par2, par3, par4, PlasmaCraft.cryoniteMoving);
		}
		else
			super.breakBlock(par1World, par2, par3, par4, par5, par6);
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
	{
		if(this == PlasmaCraft.frozenCryonite)
			blockIcon = iconRegister.registerIcon(PlasmaCraft.MOD_ID + ":frozenCryonite");
		else
			blockIcon = iconRegister.registerIcon(PlasmaCraft.MOD_ID + ":reinforcedGlass");
	}
}

package com.elvenwater.malkierian.Plasmacraft.common.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.elvenwater.malkierian.Plasmacraft.common.CommonProxy;
import com.elvenwater.malkierian.Plasmacraft.common.PlasmaCraft;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
//import net.minecraft.src.forge.ISpecialResistance;

public class BlockPlasmaOre extends BlockOre //implements  ISpecialResistance
{
	public BlockPlasmaOre(int i, int j)
	{
		super(i, j);
		setTickRandomly(true);
		setStepSound(Block.soundStoneFootstep);
		setBlockName("orePlasma");
		setCreativeTab(CreativeTabs.tabBlock);
		setRequiresSelfNotify();
	}
	
	public void addCreativeItems(ArrayList itemList)
	{
		for(int i = 0; i < 5; i++)
		{
			itemList.add(new ItemStack(this, 1, i));
		}
	}

	public int idDropped(int i, Random random)
	{
		return blockID;
	}
	
	public int damageDropped(int i)
	{
		return i;
	}
	
	public int getBlockTextureFromSideAndMetadata(int i, int j)
	{
		switch (j)
		{
		case 0:
			return PlasmaCraft.orePlutoniumIndex;
		case 1:
			return PlasmaCraft.oreRadioniteIndex;
		case 2:
			return PlasmaCraft.oreNeptuniumIndex;
		case 3:
			return PlasmaCraft.oreObsidiumIndex;
		default:
			return PlasmaCraft.oreUraniumIndex;
		}
		
	}

	public int quantityDropped(Random random)
	{
		return 1;
	}

	public void onEntityWalking(World world, int i, int j, int k, Entity entity)
	{
		onEntityCollidedWithBlock(world, i, j, k, entity);
	}

	public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity)
	{
		int meta = world.getBlockMetadata(i, j, k);
		int blockID = world.getBlockId(i, j, k);
		if(blockID == this.blockID && meta == PlasmaCraft.uraniumMeta)
		{
			entity.attackEntityFrom(DamageSource.cactus, 5);
			return;
		}
		else
		{
			return;
		}
	}

	@Override
	public String getTextureFile()
	{
		return CommonProxy.BLOCK_PNG;
	}

	@Override
	public float getExplosionResistance(Entity exploder, World world, int i, int j,
			int k, double src_x, double src_y, double src_z) {
		switch(world.getBlockMetadata(i, j, k))
		{
		case PlasmaCraft.obsidiumMeta:
			return 1200F;
		case PlasmaCraft.uraniumMeta:
			return 6F;
		case PlasmaCraft.plutoniumMeta:
			return 8F;
		default:
			return 3F;
		}
	}
	
	@Override
	public float getBlockHardness(World world, int x, int y, int z)
	{
		int md = world.getBlockMetadata(x, y, z);
		switch(md)
		{
		case PlasmaCraft.obsidiumMeta:
			return 15F;
		case PlasmaCraft.uraniumMeta:
		case PlasmaCraft.plutoniumMeta:
			return 5F;
		default:
			return 3F;
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs tab, List subItems) {
		for (int ix = 0; ix < 5; ix++) {
			subItems.add(new ItemStack(this, 1, ix));
		}
	}
}

package com.malkierian.Plasmacraft.common.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.malkierian.Plasmacraft.common.PlasmaCraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPlasmaOre extends BlockOre
{
	public static final String[] filenames = new String[] {"ore_plutonium", "ore_radionite", "ore_neptunium", "ore_obsidium", "ore_uranium"};
	@SideOnly(Side.CLIENT)
	private IIcon[] icons;
	
	public BlockPlasmaOre()
	{
		super();
		setTickRandomly(true);
		setStepSound(Block.soundTypeStone);
		setCreativeTab(CreativeTabs.tabBlock);
	}

//	@Override
//	public void addCreativeItems(ArrayList itemList)
//	{
//		for(int i = 0; i < 5; i++)
//		{
//			itemList.add(new ItemStack(this, 1, i));
//		}
//	}

	@Override
	public int damageDropped(int i)
	{
		return i;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		icons = new IIcon[filenames.length];

		for (int i = 0; i < icons.length; ++i)
		{
			icons[i] = par1IconRegister.registerIcon(filenames[i]);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int i, int j)
	{
		return icons[j];		
	}

	@Override
	public int quantityDropped(Random random)
	{
		return 1;
	}

	@Override
	public void onEntityWalking(World world, int i, int j, int k, Entity entity)
	{
		onEntityCollidedWithBlock(world, i, j, k, entity);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity)
	{
		int meta = world.getBlockMetadata(i, j, k);
		Block block = world.getBlock(i, j, k);
		if(block == this && meta == PlasmaCraft.uraniumMeta)
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
	public float getExplosionResistance(Entity exploder, World world, int i, int j,
			int k, double src_x, double src_y, double src_z)
	{
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item par1, CreativeTabs tab, List subItems)
	{
		for (int ix = 0; ix < 5; ix++)
		{
			subItems.add(new ItemStack(this, 1, ix));
		}
	}
}

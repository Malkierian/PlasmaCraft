package com.elvenwater.malkierian.Plasmacraft.common.blocks;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.elvenwater.malkierian.Plasmacraft.common.CommonProxy;
import com.elvenwater.malkierian.Plasmacraft.common.GuiIds;
import com.elvenwater.malkierian.Plasmacraft.common.PlasmaCraft;
import com.elvenwater.malkierian.Plasmacraft.common.TilePlasmaBench;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPlasmaBench extends BlockContainer
{
	private Random plasmificatorRand;
	private final boolean isActive;
	private Icon sideIcon;
	private Icon frontIdleIcon;
	private Icon frontActiveIcon;
	private static boolean keepPlasmificatorInventory = false;

	public BlockPlasmaBench(int i)
	{
		super(i, Material.rock);
		isActive = false;
		plasmificatorRand = new Random();
		setHardness(3F);
		setStepSound(Block.soundStoneFootstep);
		setLightValue(0.0f);
		setCreativeTab(CreativeTabs.tabBlock);
	}

	@Override
	public void addCreativeItems(ArrayList itemList)
	{		
		itemList.add(new ItemStack(this, 1));
	}
	
	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z)
	{
		if(world.getBlockMetadata(x, y, z) > 8)
			return 12;
		else
			return 0;
	}

	@Override
	public int idDropped(int i, Random random, int j)
	{
		return PlasmaCraft.plasmaBench.blockID;
	}

	@Override
	public void onBlockAdded(World world, int i, int j, int k)
	{
		super.onBlockAdded(world, i, j, k);
		setDefaultDirection(world, i, j, k);
	}

	private void setDefaultDirection(World world, int i, int j, int k)
	{
		if(!world.isRemote)
		{
			return;
		}
		int l = world.getBlockId(i, j, k - 1);
		int i1 = world.getBlockId(i, j, k + 1);
		int j1 = world.getBlockId(i - 1, j, k);
		int k1 = world.getBlockId(i + 1, j, k);
		byte byte0 = 3;
		if(Block.opaqueCubeLookup[l] && !Block.opaqueCubeLookup[i1])
		{
			byte0 = 3;
		}
		if(Block.opaqueCubeLookup[i1] && !Block.opaqueCubeLookup[l])
		{
			byte0 = 2;
		}
		if(Block.opaqueCubeLookup[j1] && !Block.opaqueCubeLookup[k1])
		{
			byte0 = 5;
		}
		if(Block.opaqueCubeLookup[k1] && !Block.opaqueCubeLookup[j1])
		{
			byte0 = 4;
		}
		world.setBlockMetadataWithNotify(i, j, k, byte0, 0);
	}

	@Override
	public Icon getBlockTexture(IBlockAccess iblockaccess, int i, int j, int k, int l)
	{
		int i1 = iblockaccess.getBlockMetadata(i, j, k);
		int meta = i1 > 8 ? i1 - 8 : i1; 
		if(l != meta)
		{
			return sideIcon;
		}
		if(i1 > 8)
		{
			return frontActiveIcon;
		} else
		{
			return frontIdleIcon;
		}
	}

	public void randomDisplayTick(World world, int i, int j, int k, Random random)
	{
		super.randomDisplayTick(world, i, j, k, random);
	}

	@Override
	public Icon getBlockTextureFromSideAndMetadata(int i, int j)
	{
		if(i == 3)
		{
			if(j > 8)
				return frontActiveIcon;
			else
				return frontIdleIcon;
		} else
		{
			return sideIcon;
		}
	}

	@Override
	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int par6, float par7, float par8, float par9)
	{
		// Drop through if the player is sneaking
		if(entityplayer.isSneaking())
			return false;
		
		TileEntity tileentity = world.getBlockTileEntity(i, j, k);
		
		if(tileentity == null)
			return false;
		
		if(world.isRemote)
			return true;
		else
		{
			entityplayer.openGui(PlasmaCraft.instance, GuiIds.PLASMIFICATOR, world, i, j, k);
		   	return true;
		}
	}

	public static void updatePlasmificatorBlockState(boolean flag, World world, int i, int j, int k)
	{
		int l = world.getBlockMetadata(i, j, k);
//		TileEntity tileentity = world.getBlockTileEntity(i, j, k);
//		keepPlasmificatorInventory = true;
		if(flag)
		{
			if(l < 8)
				l += 8;
		}
		else
		{
			if(l > 8)
				l -= 8;
		}
		
		//keepPlasmificatorInventory = false;
		world.setBlockMetadataWithNotify(i, j, k, l, 0);
		world.markBlockForUpdate(i, j, k);
//		if(tileentity != null)
//		{
//			tileentity.validate();
//			world.setBlockTileEntity(i, j, k, tileentity);
//		}
	}

	@Override
	public void onBlockPlacedBy(World world, int i, int j, int k, EntityLiving entityliving, ItemStack stack)
	{
		int l = MathHelper.floor_double((double)((entityliving.rotationYaw * 4F) / 360F) + 0.5D) & 3;
		if(l == 0)
		{
			world.setBlockMetadataWithNotify(i, j, k, 2, 0);
		}
		if(l == 1)
		{
			world.setBlockMetadataWithNotify(i, j, k, 5, 0);
		}
		if(l == 2)
		{
			world.setBlockMetadataWithNotify(i, j, k, 3, 0);
		}
		if(l == 3)
		{
			world.setBlockMetadataWithNotify(i, j, k, 4, 0);
		}
	}
	
	@Override
	public void onBlockDestroyedByPlayer(World world, int i, int j, int k, int l)
	{
		onBlockRemoved(world, i, j, k);
		super.onBlockDestroyedByPlayer(world, i, j, k, l);
	}
	
	@Override
	public void onBlockDestroyedByExplosion(World par1World, int par2, int par3, int par4, Explosion explosion)
	{
		onBlockRemoved(par1World, par2, par3, par4);
		super.onBlockDestroyedByExplosion(par1World, par2, par3, par4, explosion);
	}

	public void onBlockRemoved(World world, int i, int j, int k)
	{
		if(!keepPlasmificatorInventory)
		{
			TilePlasmaBench tileentityfurnace = (TilePlasmaBench)world.getBlockTileEntity(i, j, k);
			if(tileentityfurnace != null)
			{
label0:
				for(int l = 0; l < tileentityfurnace.getSizeInventory(); l++)
				{
					ItemStack itemstack = tileentityfurnace.getStackInSlot(l);
					if(itemstack == null)
					{
						continue;
					}
					float f = plasmificatorRand.nextFloat() * 0.8F + 0.1F;
					float f1 = plasmificatorRand.nextFloat() * 0.8F + 0.1F;
					float f2 = plasmificatorRand.nextFloat() * 0.8F + 0.1F;
					do
					{
						if(itemstack.stackSize <= 0)
						{
							continue label0;
						}
						int i1 = plasmificatorRand.nextInt(21) + 10;
						if(i1 > itemstack.stackSize)
						{
							i1 = itemstack.stackSize;
						}
						itemstack.stackSize -= i1;
						EntityItem entityitem = new EntityItem(world, (float)i + f, (float)j + f1, (float)k + f2, new ItemStack(itemstack.itemID, i1, itemstack.getItemDamage()));
						float f3 = 0.05F;
						entityitem.motionX = (float)plasmificatorRand.nextGaussian() * f3;
						entityitem.motionY = (float)plasmificatorRand.nextGaussian() * f3 + 0.2F;
						entityitem.motionZ = (float)plasmificatorRand.nextGaussian() * f3;
						world.spawnEntityInWorld(entityitem);
					} while(true);
				}
			}
		}
	}

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        frontActiveIcon = par1IconRegister.registerIcon("plasmaBench_active");
        frontIdleIcon = par1IconRegister.registerIcon("plasmaBench_idle");
        sideIcon = par1IconRegister.registerIcon("bench_side");
    }

	@Override
	public TileEntity createNewTileEntity(World var1)
	{
		return new TilePlasmaBench();
	}
}

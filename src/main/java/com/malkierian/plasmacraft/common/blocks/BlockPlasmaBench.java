package com.malkierian.plasmacraft.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.malkierian.plasmacraft.common.GuiIds;
import com.malkierian.plasmacraft.common.PlasmaCraft;
import com.malkierian.plasmacraft.common.TilePlasmaBench;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPlasmaBench extends BlockContainer
{
	private Random plasmificatorRand;
	private IIcon frontIdleIcon;
	private IIcon frontActiveIcon;
	private static boolean keepPlasmificatorInventory = false;

	public BlockPlasmaBench()
	{
		super(Material.rock);
		plasmificatorRand = new Random();
		setHardness(3F);
		setStepSound(Block.soundTypeStone);
		setLightLevel(0.0f);
		setCreativeTab(CreativeTabs.tabBlock);
	}

//	@Override
//	public void addCreativeItems(ArrayList itemList)
//	{		
//		itemList.add(new ItemStack(this, 1));
//	}
	
	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z)
	{
		if(world.getBlockMetadata(x, y, z) > 8)
			return 12;
		else
			return 0;
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
			Block l = world.getBlock(i, j, k - 1);
			Block i1 = world.getBlock(i, j, k + 1);
			Block j1 = world.getBlock(i - 1, j, k);
			Block k1 = world.getBlock(i + 1, j, k);
			byte byte0 = 3;
			if(l.isOpaqueCube() && !i1.isOpaqueCube())
			{
				byte0 = 3;
			}
			if(i1.isOpaqueCube() && !l.isOpaqueCube())
			{
				byte0 = 2;
			}
			if(j1.isOpaqueCube() && !k1.isOpaqueCube())
			{
				byte0 = 5;
			}
			if(k1.isOpaqueCube() && !j1.isOpaqueCube())
			{
				byte0 = 4;
			}
			world.setBlockMetadataWithNotify(i, j, k, byte0, 2);
		}
	}

	public void randomDisplayTick(World world, int i, int j, int k, Random random)
	{
		super.randomDisplayTick(world, i, j, k, random);
	}

	@Override
	public IIcon getIcon(int i, int j)
	{
		if(i == 3)
		{
			if(j > 8)
				return frontActiveIcon;
			else
				return frontIdleIcon;
		} else
		{
			return blockIcon;
		}
	}

	@Override
	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int par6, float par7, float par8, float par9)
	{
		// Drop through if the player is sneaking
		if(entityplayer.isSneaking())
			return false;
		
		TileEntity tileentity = world.getTileEntity(i, j, k);
		
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
		
		world.setBlockMetadataWithNotify(i, j, k, l, 0x02);
		world.markBlockForUpdate(i, j, k);
	}

	@Override
	public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack stack)
	{
		int l = MathHelper.floor_double((double)((entityliving.rotationYaw * 4F) / 360F) + 0.5D) & 3;
		if(l == 0)
		{
			world.setBlockMetadataWithNotify(i, j, k, 2, 0x02);
		}
		if(l == 1)
		{
			world.setBlockMetadataWithNotify(i, j, k, 5, 0x02);
		}
		if(l == 2)
		{
			world.setBlockMetadataWithNotify(i, j, k, 3, 0x02);
		}
		if(l == 3)
		{
			world.setBlockMetadataWithNotify(i, j, k, 4, 0x02);
		}
	}
	
	@Override
	public void onBlockDestroyedByPlayer(World world, int i, int j, int k, int l)
	{
		onBlockPreDestroy(world, i, j, k, 0);
		super.onBlockDestroyedByPlayer(world, i, j, k, l);
	}
	
	@Override
	public void onBlockDestroyedByExplosion(World par1World, int par2, int par3, int par4, Explosion explosion)
	{
		onBlockPreDestroy(par1World, par2, par3, par4, 0);
		super.onBlockDestroyedByExplosion(par1World, par2, par3, par4, explosion);
	}

	@Override
	public void onBlockPreDestroy(World world, int i, int j, int k, int oldMeta)
	{
		if(!keepPlasmificatorInventory)
		{
			TilePlasmaBench tileentityfurnace = (TilePlasmaBench)world.getTileEntity(i, j, k);
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
						EntityItem entityitem = new EntityItem(world, (float)i + f, (float)j + f1, (float)k + f2, new ItemStack(itemstack.getItem(), i1, itemstack.getItemDamage()));
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

	@Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        frontActiveIcon = par1IconRegister.registerIcon("plasmacraft:plasmaBench_active");
        frontIdleIcon = par1IconRegister.registerIcon("plasmacraft:plasmaBench_idle");
        blockIcon = par1IconRegister.registerIcon("plasmacraft:plasmaBench_side");
    }

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new TilePlasmaBench();
	}
}

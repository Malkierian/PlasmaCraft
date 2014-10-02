package com.malkierian.plasmacraft.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import com.malkierian.plasmacraft.common.PlasmaCraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockAcidBarrier extends Block
{
	public BlockAcidBarrier()
	{
		super(Material.glass);
		setHardness(3F);
		setResistance(5F);
		setLightOpacity(2);
		setLightLevel(1.0F);
		setStepSound(Block.soundTypeGlass);
		setCreativeTab(PlasmaCraft.plasmaTab);
	}
	
//	public void addCreativeItems(ArrayList itemList)
//	{		
//		itemList.add(new ItemStack(this, 1));
//	}

	@Override
	public int getRenderBlockPass()
	{
		return 1;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public void onEntityWalking(World world, int i, int j, int k, Entity entity)
	{
		entity.attackEntityFrom(DamageSource.magic, 2);
	}

	@Override
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
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon(PlasmaCraft.MOD_ID + ":acidBarrier");
	}
}

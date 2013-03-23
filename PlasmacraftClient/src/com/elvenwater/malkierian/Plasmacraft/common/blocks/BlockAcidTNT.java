package com.elvenwater.malkierian.Plasmacraft.common.blocks;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTNT;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import com.elvenwater.malkierian.Plasmacraft.common.EntityAcidTNTPrimed;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockAcidTNT extends BlockTNT
{
	private Icon topIcon;
	private Icon bottomIcon;

	public BlockAcidTNT(int i)
	{
		super(i);
		setHardness(0.0F);
		setStepSound(Block.soundGrassFootstep);
	}
	
	public void addCreativeItems(ArrayList itemList)
	{		
		itemList.add(new ItemStack(this, 1));
	}

	public Icon getBlockTextureFromSideAndMetadata(int i, int j)
	{
		if(i == 0)
		{
			return bottomIcon;
		}
		if(i == 1)
		{
			return topIcon;
		} else
		{
			return field_94336_cN;
		}
	}

	@Override
	public void onBlockDestroyedByExplosion(World world, int i, int j, int k, Explosion explosion)
	{
		if(!world.isRemote)
		{
			EntityAcidTNTPrimed entityacidtntprimed = new EntityAcidTNTPrimed(world, (float)i + 0.5F, (float)j + 0.5F, (float)k + 0.5F);
			entityacidtntprimed.fuse = world.rand.nextInt(entityacidtntprimed.fuse / 4) + entityacidtntprimed.fuse / 8;
			world.spawnEntityInWorld(entityacidtntprimed);
		}
	}

	@Override
	public void onBlockDestroyedByPlayer(World world, int i, int j, int k, int l)
	{
		if(!world.isRemote)
		{
			if((l & 1) == 1)
			{
				EntityAcidTNTPrimed entityacidtntprimed = new EntityAcidTNTPrimed(world, (float)i + 0.5F, (float)j + 0.5F, (float)k + 0.5F);
				world.spawnEntityInWorld(entityacidtntprimed);
				world.playSoundAtEntity(entityacidtntprimed, "random.fuse", 1.0F, 1.0F);
				return;
			}
		}
	}

    @SideOnly(Side.CLIENT)
    public void func_94332_a(IconRegister par1IconRegister)
    {
        field_94336_cN = par1IconRegister.func_94245_a(func_94330_A());
        topIcon = par1IconRegister.func_94245_a(func_94330_A() + "_top");
        bottomIcon = par1IconRegister.func_94245_a(func_94330_A() + "_bottom");
    }
}

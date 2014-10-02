package com.malkierian.plasmacraft.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTNT;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import com.malkierian.plasmacraft.common.EntityAcidTNTPrimed;
import com.malkierian.plasmacraft.common.PlasmaCraft;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockAcidTNT extends BlockTNT
{
	private IIcon topIcon;
	private IIcon bottomIcon;

	public BlockAcidTNT()
	{
		super();
		setHardness(0.0F);
		setStepSound(Block.soundTypeGrass);
		setCreativeTab(PlasmaCraft.plasmaTab);
	}
	
//	public void addCreativeItems(ArrayList itemList)
//	{		
//		itemList.add(new ItemStack(this, 1));
//	}

	@Override
	public IIcon getIcon(int i, int j)
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
			return blockIcon;
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

	@Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        blockIcon = par1IconRegister.registerIcon("plasmacraft:acidTnt");
        topIcon = par1IconRegister.registerIcon("plasmacraft:acidTnt_top");
        bottomIcon = par1IconRegister.registerIcon("plasmacraft:acidTnt_bottom");
    }
}

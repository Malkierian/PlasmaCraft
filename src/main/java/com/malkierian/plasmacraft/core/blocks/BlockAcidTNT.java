package com.malkierian.plasmacraft.core.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTNT;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import com.malkierian.plasmacraft.core.PlasmaCraft;
import com.malkierian.plasmacraft.core.entities.EntityAcidTNTPrimed;

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
	public void onBlockDestroyedByExplosion(World p_149723_1_, int p_149723_2_, int p_149723_3_, int p_149723_4_, Explosion p_149723_5_)
    {
        if (!p_149723_1_.isRemote)
        {
            EntityTNTPrimed entitytntprimed = new EntityTNTPrimed(p_149723_1_, (double)((float)p_149723_2_ + 0.5F), (double)((float)p_149723_3_ + 0.5F), (double)((float)p_149723_4_ + 0.5F), p_149723_5_.getExplosivePlacedBy());
            entitytntprimed.fuse = p_149723_1_.rand.nextInt(entitytntprimed.fuse / 4) + entitytntprimed.fuse / 8;
            p_149723_1_.spawnEntityInWorld(entitytntprimed);
        }
    }

    /**
     * Called right before the block is destroyed by a player.  Args: world, x, y, z, metaData
     */
    public void onBlockDestroyedByPlayer(World p_149664_1_, int p_149664_2_, int p_149664_3_, int p_149664_4_, int p_149664_5_)
    {
        this.func_150114_a(p_149664_1_, p_149664_2_, p_149664_3_, p_149664_4_, p_149664_5_, (EntityLivingBase)null);
    }

    public void func_150114_a(World p_150114_1_, int p_150114_2_, int p_150114_3_, int p_150114_4_, int p_150114_5_, EntityLivingBase p_150114_6_)
    {
        if (!p_150114_1_.isRemote)
        {
            if ((p_150114_5_ & 1) == 1)
            {
                EntityAcidTNTPrimed entitytntprimed = new EntityAcidTNTPrimed(p_150114_1_, (double)((float)p_150114_2_ + 0.5F), (double)((float)p_150114_3_ + 0.5F), (double)((float)p_150114_4_ + 0.5F), p_150114_6_);
                p_150114_1_.spawnEntityInWorld(entitytntprimed);
                p_150114_1_.playSoundAtEntity(entitytntprimed, "game.tnt.primed", 1.0F, 1.0F);
            }
        }
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
        if (p_149727_5_.getCurrentEquippedItem() != null && p_149727_5_.getCurrentEquippedItem().getItem() == Items.flint_and_steel)
        {
            this.func_150114_a(p_149727_1_, p_149727_2_, p_149727_3_, p_149727_4_, 1, p_149727_5_);
            p_149727_1_.setBlockToAir(p_149727_2_, p_149727_3_, p_149727_4_);
            p_149727_5_.getCurrentEquippedItem().damageItem(1, p_149727_5_);
            return true;
        }
        else
        {
            return super.onBlockActivated(p_149727_1_, p_149727_2_, p_149727_3_, p_149727_4_, p_149727_5_, p_149727_6_, p_149727_7_, p_149727_8_, p_149727_9_);
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

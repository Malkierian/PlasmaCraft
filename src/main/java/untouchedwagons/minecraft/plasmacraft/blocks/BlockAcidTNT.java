package untouchedwagons.minecraft.plasmacraft.blocks;

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

import untouchedwagons.minecraft.plasmacraft.PlasmaCraft;
import untouchedwagons.minecraft.plasmacraft.entities.EntityAcidTNTPrimed;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockAcidTNT extends BlockTNT
{
	private IIcon topIcon;
	private IIcon bottomIcon;

	public BlockAcidTNT()
	{
        this.setHardness(0.0F);
		this.setStepSound(Block.soundTypeGrass);
        this.setCreativeTab(PlasmaCraft.plasmaTab);
	}

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
	public void onBlockDestroyedByExplosion(World world, int x, int y, int z, Explosion explosion)
    {
        if (!world.isRemote)
        {
            EntityTNTPrimed entitytntprimed = new EntityTNTPrimed(world, (double)((float)x + 0.5F), (double)((float)y + 0.5F), (double)((float)z + 0.5F), explosion.getExplosivePlacedBy());
            entitytntprimed.fuse = world.rand.nextInt(entitytntprimed.fuse / 4) + entitytntprimed.fuse / 8;
            world.spawnEntityInWorld(entitytntprimed);
        }
    }

    public void func_150114_a(World world, int x, int y, int z, int p_150114_5_, EntityLivingBase p_150114_6_)
    {
        if (!world.isRemote)
        {
            if ((p_150114_5_ & 1) == 1)
            {
                EntityAcidTNTPrimed entitytntprimed = new EntityAcidTNTPrimed(world, x + 0.5F, y + 0.5F, z + 0.5F, p_150114_6_);
                world.spawnEntityInWorld(entitytntprimed);
                world.playSoundAtEntity(entitytntprimed, "game.tnt.primed", 1.0F, 1.0F);
            }
        }
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hit_x, float hit_y, float hit_z)
    {
        if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == Items.flint_and_steel)
        {
            this.func_150114_a(world, x, y, z, 1, player);
            world.setBlockToAir(x, y, z);
            player.getCurrentEquippedItem().damageItem(1, player);
            return true;
        }
        else
        {
            return super.onBlockActivated(world, x, y, z, player, side, hit_x, hit_y, hit_z);
        }
    }

	@Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister icon_registrar)
    {
        blockIcon = icon_registrar.registerIcon("plasmacraft:acidTnt");
        topIcon = icon_registrar.registerIcon("plasmacraft:acidTnt_top");
        bottomIcon = icon_registrar.registerIcon("plasmacraft:acidTnt_bottom");
    }
}

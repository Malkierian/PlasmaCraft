package untouchedwagons.minecraft.plasmacraft.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
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

import untouchedwagons.minecraft.plasmacraft.client.gui.GuiIds;
import untouchedwagons.minecraft.plasmacraft.PlasmaCraft;
import untouchedwagons.minecraft.plasmacraft.tileentity.TilePlasmaBench;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPlasmaBench extends BlockContainer
{
	private Random plasmificatorRand;
	private IIcon frontIdleIcon;
	private IIcon frontActiveIcon;

	public BlockPlasmaBench()
	{
		super(Material.rock);
		plasmificatorRand = new Random();
		setHardness(3F);
		setStepSound(Block.soundTypeStone);
		setLightLevel(0.0f);
		setCreativeTab(PlasmaCraft.plasmaTab);
	}
	
	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z)
	{
        return world.getBlockMetadata(x, y, z) > 8 ? 12 : 0;
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		super.onBlockAdded(world, x, y, z);
		setDefaultDirection(world, x, y, z);
	}

	private void setDefaultDirection(World world, int x, int y, int z)
	{
        if (world.isRemote) return;

        Block l = world.getBlock(x, y, z - 1);
        Block i1 = world.getBlock(x, y, z + 1);
        Block j1 = world.getBlock(x - 1, y, z);
        Block k1 = world.getBlock(x + 1, y, z);

        byte byte0 = 3;

        if(l.func_149730_j() && !i1.func_149730_j())
        {
            byte0 = 3;
        }
        if(i1.func_149730_j() && !l.func_149730_j())
        {
            byte0 = 2;
        }
        if(j1.func_149730_j() && !k1.func_149730_j())
        {
            byte0 = 5;
        }
        if(k1.func_149730_j() && !j1.func_149730_j())
        {
            byte0 = 4;
        }
        world.setBlockMetadataWithNotify(x, y, z, byte0, 2);
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
		
		if(world.isRemote)
			return true;

        entityplayer.openGui(PlasmaCraft.instance, GuiIds.PLASMIFICATOR, world, i, j, k);
        return true;
	}

	public static void updatePlasmificatorBlockState(boolean flag, World world, int x, int y, int z)
	{
		int l = world.getBlockMetadata(x, y, z);
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
		
		world.setBlockMetadataWithNotify(x, y, z, l, 0x02);
		world.markBlockForUpdate(x, y, z);
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityliving, ItemStack stack)
	{
		int l = MathHelper.floor_double((double)((entityliving.rotationYaw * 4F) / 360F) + 0.5D) & 3;
		if(l == 0)
		{
			world.setBlockMetadataWithNotify(x, y, z, 2, 0x02);
		}
		if(l == 1)
		{
			world.setBlockMetadataWithNotify(x, y, z, 5, 0x02);
		}
		if(l == 2)
		{
			world.setBlockMetadataWithNotify(x, y, z, 3, 0x02);
		}
		if(l == 3)
		{
			world.setBlockMetadataWithNotify(x, y, z, 4, 0x02);
		}
	}
	
	@Override
	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int meta)
	{
		onBlockPreDestroy(world, x, y, z, 0);
		super.onBlockDestroyedByPlayer(world, x, y, z, meta);
	}
	
	@Override
	public void onBlockDestroyedByExplosion(World par1World, int x, int y, int z, Explosion explosion)
	{
		onBlockPreDestroy(par1World, x, y, z, 0);
		super.onBlockDestroyedByExplosion(par1World, x, y, z, explosion);
	}

	@Override
	public void onBlockPreDestroy(World world, int x, int y, int z, int oldMeta)
	{
        TilePlasmaBench tileentityfurnace = (TilePlasmaBench)world.getTileEntity(x, y, z);
        if(tileentityfurnace == null)
        {
            return;
        }

        for(int l = 0; l < tileentityfurnace.getSizeInventory(); l++)
        {
            ItemStack itemstack = tileentityfurnace.getStackInSlot(l);

            if(itemstack == null || itemstack.stackSize == 0) continue;

            float f = plasmificatorRand.nextFloat() * 0.8F + 0.1F;
            float f1 = plasmificatorRand.nextFloat() * 0.8F + 0.1F;
            float f2 = plasmificatorRand.nextFloat() * 0.8F + 0.1F;

            EntityItem entityitem = new EntityItem(world, (float)x + f, (float)y + f1, (float)z + f2, itemstack);
            float f3 = 0.05F;

            entityitem.motionX = (float)plasmificatorRand.nextGaussian() * f3;
            entityitem.motionY = (float)plasmificatorRand.nextGaussian() * f3 + 0.2F;
            entityitem.motionZ = (float)plasmificatorRand.nextGaussian() * f3;
            world.spawnEntityInWorld(entityitem);
        }
	}

	@Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister icon_registrar)
    {
        frontActiveIcon = icon_registrar.registerIcon("plasmacraft:plasmaBench_active");
        frontIdleIcon = icon_registrar.registerIcon("plasmacraft:plasmaBench_idle");
        blockIcon = icon_registrar.registerIcon("plasmacraft:plasmaBench_side");
    }

	@Override
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return new TilePlasmaBench();
	}
}

package net.minecraft.src.Plasmacraft;

import java.util.Random;

import net.minecraft.src.Block;
import net.minecraft.src.BlockContainer;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.Material;
import net.minecraft.src.MathHelper;
import net.minecraft.src.ModLoader;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import net.minecraft.src.forge.ITextureProvider;

public class BlockPlasmificator extends BlockContainer implements ITextureProvider
{
    private final boolean isActive;
    private final int frontIdleTexture;
    private final int frontActiveTexture;

    protected BlockPlasmificator(int i, int j, int k, int l, boolean flag, float lightValue)
    {
        super(i, Material.rock);
        isActive = flag;
        blockIndexInTexture = j;
        frontIdleTexture = k;
        frontActiveTexture = l;
        setHardness(3F);
        setStepSound(Block.soundStoneFootstep);
        setLightValue(lightValue);
    }

    public int idDropped(int i, Random random)
    {
        return PlasmaCraftCore.plasmificatorIdle.blockID;
    }

    public void onBlockAdded(World world, int i, int j, int k)
    {
        super.onBlockAdded(world, i, j, k);
        setDefaultDirection(world, i, j, k);
    }

    private void setDefaultDirection(World world, int i, int j, int k)
    {
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
        world.setBlockMetadataWithNotify(i, j, k, byte0);
    }

    public int getBlockTexture(IBlockAccess iblockaccess, int i, int j, int k, int l)
    {
        int i1 = iblockaccess.getBlockMetadata(i, j, k);
        if(l != i1)
        {
            return blockIndexInTexture;
        }
        if(isActive)
        {
            return frontActiveTexture;
        } else
        {
            return frontIdleTexture;
        }
    }

    public void randomDisplayTick(World world, int i, int j, int k, Random random)
    {
        if(!isActive)
        {
            return;
        } else
        {
            return;
        }
    }

    public int getBlockTextureFromSide(int i)
    {
        if(i == 3)
        {
            return frontIdleTexture;
        } else
        {
            return blockIndexInTexture;
        }
    }

    public boolean blockActivated(World world, int i, int j, int k, EntityPlayer entityplayer)
    {
        if(world.multiplayerWorld)
        {
            TileEntityPlasmaBench smtileentityplasmabench = (TileEntityPlasmaBench)world.getBlockTileEntity(i, j, k);
            //ModLoader.OpenGUI(entityplayer, new GuiPlasmaBench(entityplayer.inventory, smtileentityplasmabench));
            return true;
        } else
        {
            TileEntityPlasmaBench smtileentityplasmabench1 = (TileEntityPlasmaBench)world.getBlockTileEntity(i, j, k);
            //ModLoader.OpenGUI(entityplayer, new GuiPlasmaBench(entityplayer.inventory, smtileentityplasmabench1));
            return true;
        }
    }

    public static void updatePlasmificatorBlockState(boolean flag, World world, int i, int j, int k)
    {
        int l = world.getBlockMetadata(i, j, k);
        TileEntity tileentity = world.getBlockTileEntity(i, j, k);
        if(flag)
        {
            world.setBlockWithNotify(i, j, k, PlasmaCraftCore.plasmificatorActive.blockID);
        }
        else
        {
            world.setBlockWithNotify(i, j, k, PlasmaCraftCore.plasmificatorIdle.blockID);
        }
        world.setBlockMetadataWithNotify(i, j, k, l);
        world.setBlockTileEntity(i, j, k, tileentity);
    }

    public TileEntity getBlockEntity()
    {
        return new TileEntityPlasmaBench();
    }

    public void onBlockPlacedBy(World world, int i, int j, int k, EntityLiving entityliving)
    {
        int l = MathHelper.floor_double((double)((entityliving.rotationYaw * 4F) / 360F) + 0.5D) & 3;
        if(l == 0)
        {
            world.setBlockMetadataWithNotify(i, j, k, 2);
        }
        if(l == 1)
        {
            world.setBlockMetadataWithNotify(i, j, k, 5);
        }
        if(l == 2)
        {
            world.setBlockMetadataWithNotify(i, j, k, 3);
        }
        if(l == 3)
        {
            world.setBlockMetadataWithNotify(i, j, k, 4);
        }
    }

	@Override
	public String getTextureFile()
	{
		return PlasmaCraftCore.terrainTexture;
	}
}
